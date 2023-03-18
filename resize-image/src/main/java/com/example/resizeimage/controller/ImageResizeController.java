package com.example.resizeimage.controller;

import com.example.resizeimage.service.ImageResizeService;
import com.example.resizeimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images-resize")
public class ImageResizeController {
    private final ImageResizeService imageResizeService;
    // Upload file
    @PostMapping("")
    public String uploadImage(@ModelAttribute("file") MultipartFile file) {
        return imageResizeService.uploadImage(file);
    }

    // Xem file
    @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] readImage(@PathVariable Integer id) {
        return imageResizeService.readImage(id);
    }
}
