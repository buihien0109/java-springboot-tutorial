package com.example.jobhunt.controller;

import com.example.jobhunt.constant.Constant;
import com.example.jobhuntbackend.service.FileService;
import com.example.jobhuntbackend.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/files")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("")
    public String uploadFile(@ModelAttribute("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> readFile(@PathVariable String fileName) {
        byte[] bytes = fileService.readFile(fileName);

        String fileExtension = Utils.getFileExtension(fileName);
        if(Constant.fileExtension.contains(fileExtension)) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bytes);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
