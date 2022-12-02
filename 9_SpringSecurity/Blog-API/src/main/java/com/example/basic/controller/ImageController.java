package com.example.basic.controller;

import com.example.basic.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("admin/images")
    public ResponseEntity<?> getImages() {
        return ResponseEntity.ok(imageService.getImages());
    }

    @GetMapping("images/{id}")
    public ResponseEntity<?> readImage(@PathVariable Integer id) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.readImage(id));
    }

    @PostMapping("admin/images")
    public ResponseEntity<?> uploadImage(@ModelAttribute("file")MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadFile(file));
    }

    @DeleteMapping("admin/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
