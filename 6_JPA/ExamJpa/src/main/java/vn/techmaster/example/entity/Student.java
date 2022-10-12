package vn.techmaster.example.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    @JsonIgnore
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    @JsonGetter(value = "courses")
    @Transient
    public Map<String, Integer> getCourses() {
        Map<String, Integer> courseScore = new HashMap<>();
        studentCourses.forEach(studentCourse -> {
                    courseScore.put(studentCourse.getCourse().getName(), studentCourse.getScore());
                }
        );
        return courseScore;
    }
}
