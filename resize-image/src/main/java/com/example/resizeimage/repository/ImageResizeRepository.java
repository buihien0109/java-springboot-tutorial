package com.example.resizeimage.repository;

import com.example.resizeimage.entity.ImageResize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageResizeRepository extends JpaRepository<ImageResize, Integer> {
}