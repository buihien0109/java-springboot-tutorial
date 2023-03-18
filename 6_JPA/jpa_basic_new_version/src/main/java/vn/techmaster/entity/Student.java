package vn.techmaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.techmaster.generate.StudentIdGenerate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(generator = "student-generator")
    @GenericGenerator(name = "student-generator",
            parameters = {
                    @Parameter(name = "prefix", value = "abc"),
            },
            strategy = "vn.techmaster.generate.StudentIdGenerate")
    private String id;
}
