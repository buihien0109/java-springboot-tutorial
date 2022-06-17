package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Image;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, String> {
    List<Image> getByUserIdOrderByUploadedAtDesc(int userId, Sort sort);
}
