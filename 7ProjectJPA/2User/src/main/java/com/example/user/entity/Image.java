package com.example.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
