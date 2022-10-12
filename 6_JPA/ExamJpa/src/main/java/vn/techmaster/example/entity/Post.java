package vn.techmaster.example.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "post"),
            strategy = "vn.techmaster.example.generator.CustomGeneratorOther")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;
}