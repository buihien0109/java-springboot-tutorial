package com.example.jpanew.generateId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comic")
public class Comic {
    @Id
    @GeneratedValue(generator = "comic-generator")
    @GenericGenerator(name = "comic-generator",
            parameters = @Parameter(name = "prefix", value = "comic"),
            strategy = "com.example.jpanew.generateId.ComicGenerateId")
    private String id;

    private String name;

    public Comic(String name) {
        this.name = name;
    }
}
