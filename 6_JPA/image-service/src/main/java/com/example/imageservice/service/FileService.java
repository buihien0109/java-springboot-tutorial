package com.example.imageservice.service;

import com.example.imageservice.entity.Image;
import com.example.imageservice.entity.User;
import com.example.imageservice.exception.BadRequestException;
import com.example.imageservice.exception.NotFoundException;
import com.example.imageservice.repository.ImageRepository;
import com.example.imageservice.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
    private final ImageRepository imageRepository;

    public String uploadFile(User user, MultipartFile file) throws IOException {
        // Validate file
        validateFile(file);

        Image image = new Image();
        image.setData(file.getBytes());
        image.setUploadedAt(LocalDateTime.now());
        image.setUser(user);

        Image imageDb = imageRepository.save(image);

        return "/api/v1/users/" + user.getId() + "/files/" + imageDb.getId();
    }

    // Một số validate với file
    public void validateFile(MultipartFile file) {
        // Kiểm tra file
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Lấy extension file
        String fileExtension = Utils.getFileExtension(fileName);

        // Kiểm tra extension file có hợp lệ hay không
        if (!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Check size file
        if ((double) file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    // Xử lý phần read file
    public byte[] readFile(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("image with id = " + fileId + " not found");
        });

        return image.getData();
    }

    // Lấy danh sách file upload của userI
    public List<String> getFiles(int id) {
        List<Image> images = imageRepository.findByUser_Id(id);
        return images.stream()
                .map(image -> "/api/v1/users/" + id + "/files/" + image.getId())
                .collect(Collectors.toList());
    }

    // Xử lý phần xóa file
    public void deleteFile(Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(() -> {
            throw new NotFoundException("image with id = " + fileId + " not found");
        });
        imageRepository.delete(image);
    }
}
