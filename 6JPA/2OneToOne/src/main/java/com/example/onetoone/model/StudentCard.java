package com.example.onetoone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_card")
public class StudentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code")
    private String code;

    @OneToOne(mappedBy = "studentCard", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("student_card")
    private Student student;

    public String printInfo() {
        return "StudentCard{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", student=" + student +
                '}';
    }
}