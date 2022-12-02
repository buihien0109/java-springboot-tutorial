package com.example.basic.service;

import com.example.basic.entity.Image;
import com.example.basic.entity.User;
import com.example.basic.exception.BadRequestException;
import com.example.basic.exception.NotFoundException;
import com.example.basic.repository.ImageRepository;
import com.example.basic.security.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final AuthenticationFacade authenticationFacade;

    public List<String> getImages() {
        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
        List<Image> images = imageRepository.findByUser_IdOrderByUploadedAtDesc(user.getId());
        return images.stream()
                .map(image -> "/api/images/" + image.getId())
                .collect(Collectors.toList());
    }

    public String uploadFile(MultipartFile file) {
        validate(file);

        try {
            User user = (User) authenticationFacade.getAuthentication().getPrincipal();

            Image image = new Image();
            image.setUploadedAt(LocalDateTime.now());
            image.setData(file.getBytes());
            image.setUser(user);

            imageRepository.save(image);
            return "/api/images/" + image.getId();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    public void validate(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Kiểm tra tên file
        if(fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // avatar.png -> png
        // image.jpg -> jpg
        // Kiểm tra đuôi file
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new BadRequestException("Định dạng file không hợp lệ");
        }

        // Kiểm tra dung lượng file (<= 2MB)
        if((double) file.getSize() / 1_048_576L > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if(lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    private boolean checkFileExtension(String fileExtension) {
        List<String> extensions = Arrays.asList("jpg", "png", "jpeg");
        return extensions.contains(fileExtension.toLowerCase());
    }


    public byte[] readImage(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found image with id = " + fileId);
        });
        return image.getData();
    }

    public void deleteImage(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("Not found image with id = " + fileId);
        });

        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
        if(!Objects.equals(user.getId(), image.getUser().getId())) {
            throw new BadRequestException("Error delete image");
        }
        imageRepository.delete(image);
    }
}
