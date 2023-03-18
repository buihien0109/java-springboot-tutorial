package com.example.resizeimage.service;

import com.example.resizeimage.entity.ImageResize;
import com.example.resizeimage.exception.NotFoundException;
import com.example.resizeimage.repository.ImageRepository;
import com.example.resizeimage.repository.ImageResizeRepository;
import com.example.resizeimage.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageResizeService {
    private final ImageRepository imageRepository;
    private final ImageResizeRepository imageResizeRepository;

    private final FileService fileService;

    // Upload file
    public String uploadImage(MultipartFile file) {
        fileService.validateFile(file);
        return compressImage(file);
    }

    private String compressImage(MultipartFile file) {
        float quality = 0.1f;
        String imageName = file.getOriginalFilename();
        String imageExtension = FileUtils.getFileExtension(imageName);
        // Returns an Iterator containing all currently registered ImageWriters that claim to be able to encode the named format.
        // You don't have to register one yourself; some are provided.
        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Check the api value that suites your needs.
        // A compression quality setting of 0.0 is most generically interpreted as "high compression is important,"
        // while a setting of 1.0 is most generically interpreted as "high image quality is important."
        imageWriteParam.setCompressionQuality(quality);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // MemoryCacheImageOutputStream: An implementation of ImageOutputStream that writes its output to a regular
        // OutputStream, i.e. the ByteArrayOutputStream.
        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(baos);
        // Sets the destination to the given ImageOutputStream or other Object.
        imageWriter.setOutput(imageOutputStream);
        BufferedImage originalImage = null;
        try (InputStream inputStream = file.getInputStream()) {
            originalImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            String info = String.format("compressImage - bufferedImage (file %s)- IOException - message: %s ", imageName, e.getMessage());
            log.error(info);
        }
        IIOImage image = new IIOImage(originalImage, null, null);
        try {
            imageWriter.write(null, image, imageWriteParam);
        } catch (IOException e) {
            String info = String.format("compressImage - imageWriter (file %s)- IOException - message: %s ", imageName, e.getMessage());
            log.error(info);
        } finally {
            imageWriter.dispose();
        }

        // Save Image
        ImageResize imageResize = new ImageResize();
        imageResize.setUploadedAt(LocalDateTime.now());
        imageResize.setData(baos.toByteArray());
        imageResize.setWidth(originalImage.getWidth());
        imageResize.setHeight(originalImage.getHeight());
        imageResize.setSize((double) baos.size() / 1_048_576);
        imageResizeRepository.save(imageResize);

        // Info image
        log.info("size : {}", baos.size());
        log.info("height {}: ", originalImage.getHeight());
        log.info("width : {}", originalImage.getWidth());
        log.info("size 1 : {}", originalImage.getData().getDataBuffer().getSize());

        return "/api/images-resize/" + imageResize.getId();
    }

    // Đọc file
    public byte[] readImage(Integer id) {
        ImageResize imageResize = imageResizeRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found image-resize with id = " + id);
        });
        return imageResize.getData();
    }
}
