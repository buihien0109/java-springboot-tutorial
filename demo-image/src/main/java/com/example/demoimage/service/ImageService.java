package com.example.demoimage.service;


import com.example.demoimage.entity.Image;
import com.example.demoimage.exception.NotFoundException;
import com.example.demoimage.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final FileService fileService;

    // Upload file
    public String uploadImage(MultipartFile file) {
        fileService.validateFile(file);

        try {
            Image image = new Image();
            image.setUploadedAt(LocalDateTime.now());
            image.setData(file.getBytes());

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); //ImageIO는 stream을 닫아버려서 stream 재활용 불가.
            Integer width = bufferedImage.getWidth();
            Integer height = bufferedImage.getHeight();
            image.setWidth(width);
            image.setHeight(height);

            double sizeFile = (double) file.getSize() / 1_048_576L;
            image.setSize(sizeFile);

            imageRepository.save(image);
            return "/api/images/" + image.getId();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Đọc file
    public byte[] readImage(Integer id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found image with id = " + id);
        });
        return image.getData();
    }
}
