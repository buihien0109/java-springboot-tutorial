package com.example.jpanew.compositeIdGenerate;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @EmbeddedId
    @GeneratedValue(generator = "employee-generator")
    @GenericGenerator(name = "employee-generator",
            strategy = "com.example.jpanew.compositeIdGenerate.EmployeeGenerateId")
    private EmployeeIds employeeIds;

    private Integer salary;

    public Employee(Integer salary) {
        this.salary = salary;
    }
}