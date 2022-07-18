package com.example.onetoone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "point")
    private Double point;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_card_id")
//    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("student")
    private StudentCard studentCard;

    public String printInfo() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", point=" + point +
                ", studentCard=" + studentCard +
                '}';
    }
}