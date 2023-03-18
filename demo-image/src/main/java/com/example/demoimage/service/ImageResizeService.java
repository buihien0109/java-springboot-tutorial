package com.example.demoimage.service;


import com.example.demoimage.entity.ImageResize;
import com.example.demoimage.exception.NotFoundException;
import com.example.demoimage.repository.ImageResizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageResizeService {
    private final ImageResizeRepository imageResizeRepository;

    // Đọc file
    public byte[] readImage(Integer id) {
        ImageResize imageResize = imageResizeRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found image-resize with id = " + id);
        });
        return imageResize.getData();
    }
}
