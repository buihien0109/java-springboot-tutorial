package com.example.basic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Lob // Large Object
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
