package com.example.autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {
    private int id;
    private String name;
    private String email;

    // Inject bean qua field
    @Autowired
    @Qualifier("bus")
    private Vehicle vehicle;

    // Inject bean qua constructor
    // Sử dụng @Qualifier + Bean name -> để đánh dấu bean được ưu tiên khi inject
//    @Autowired
//    public Student(@Qualifier("bus") Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }

    // Inject bean qua setter
    // Sử dụng @Qualifier + Bean name -> để đánh dấu bean được ưu tiên khi inject
//    @Autowired
//    public void setVehicle(@Qualifier("bus") Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
}
