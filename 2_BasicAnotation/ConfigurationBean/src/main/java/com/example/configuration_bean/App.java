package com.example.configuration_bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration là một Annotation đánh dấu trên một Class
// cho phép Spring Boot biết được đây là nơi định nghĩa ra các Bean.
@Configuration
public class App {
    // Khởi tạo bean "Vehicle" từ class "Bus"
    @Bean("bus")
    public Vehicle getBus() {
        return new Bus();
    }

    // Khởi tạo bean "Vehicle" từ class "Motorbike"
    @Bean("motorbike")
    public Vehicle getMotorbike() {
        return new Motorbike();
    }

    // Khởi tạo bean "Vehicle" từ class "Car"
    @Bean("car")
    public Vehicle getCar() {
        return new Car();
    }

    // Khởi tạo bean "Student" và inject bean "Vehicle" vào để khởi tạo
    @Bean
    public Student getStudent(@Qualifier("car") Vehicle vehicle) {
        return new Student(vehicle);
    }
}
