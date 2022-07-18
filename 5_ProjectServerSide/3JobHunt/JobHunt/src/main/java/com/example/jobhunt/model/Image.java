package com.example.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private String id;
    private String url;
    private LocalDateTime uploadedAt;
    private int userId;
}
