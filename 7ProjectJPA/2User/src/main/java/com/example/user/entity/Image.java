package com.example.user.entity;

import com.example.user.model.dto.ImageDto;
import com.example.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@SqlResultSetMapping(
        name = "imagesInfo",
        classes = @ConstructorResult(
                targetClass = ImageDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "url"),
                        @ColumnResult(name = "user_id")
                }
        )
)
@NamedNativeQuery(
        name = "getImagesInfoOfUserId",
        resultSetMapping = "imagesInfo",
        query = "SELECT image.id, image.url, image.user_id " +
                "FROM image " +
                "WHERE image.user_id = ?1 " +
                "ORDER BY image.uploaded_at DESC"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class Image {
    @Id
    private String id;
    private String url;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "user_id")
    private int userId;
}
