package com.example.jpanew.save_json;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "brand", columnDefinition = "json")
    @Type(JsonType.class)
    private Brand brand;

    @Column(name = "category", columnDefinition = "json")
    @Type(JsonType.class)
    private List<String> category;

    public Laptop(Brand brand, List<String> category) {
        this.brand = brand;
        this.category = category;
    }
}