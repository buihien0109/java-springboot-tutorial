package com.example.user.repository;

import com.example.user.entity.Image;
import com.example.user.model.dto.ImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
//    @Query("select i.url from Image i where i.userId = :id")
//    List<String> getImagesByUserId(@Param("id") int id);

    @Query(nativeQuery = true, name = "getImagesInfoOfUserId")
    List<ImageDto> getImagesInfoOfUserId(int userId);
}
