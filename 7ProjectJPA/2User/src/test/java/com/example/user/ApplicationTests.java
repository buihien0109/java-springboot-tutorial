package com.example.user;

import com.example.user.model.dto.ImageDto;
import com.example.user.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void getImageOfUserTest() {
        List<ImageDto> images = imageRepository.getImagesInfoOfUserId(1);

//        images.forEach(System.out::println);
    }
}
