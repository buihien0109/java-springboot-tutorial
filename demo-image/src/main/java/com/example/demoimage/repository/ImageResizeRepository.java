package com.example.demoimage.repository;


import com.example.demoimage.entity.ImageResize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageResizeRepository extends JpaRepository<ImageResize, Integer> {
}