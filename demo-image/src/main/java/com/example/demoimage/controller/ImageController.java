package com.example.demoimage.controller;

import com.example.demoimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImageController {
    private final ImageService imageService;
    // Upload file
    @PostMapping("")
    public String uploadImage(@ModelAttribute("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }

    // Xem file
    @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] readImage(@PathVariable Integer id) {
        return imageService.readImage(id);
    }
}
