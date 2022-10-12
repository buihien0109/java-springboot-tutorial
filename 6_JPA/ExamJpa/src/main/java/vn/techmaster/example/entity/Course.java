package vn.techmaster.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
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
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    public Course(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "course", orphanRemoval = true)
    @JsonBackReference
    private List<StudentCourse> studentCourses = new ArrayList<>();

    @JsonGetter(value = "students")
    @Transient
    public Map<String, Integer> getStudents() {
        Map<String, Integer> studentScore = new HashMap<>();
        studentCourses.forEach(studentSubject -> {
            studentScore.put(studentSubject.getStudent().getName(), studentSubject.getScore());
        });
        return studentScore;
    }
}
