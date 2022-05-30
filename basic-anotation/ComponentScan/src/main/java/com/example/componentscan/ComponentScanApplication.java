package com.example.componentscan;

import com.example.componentscan.model.Student;
import com.example.componentscan.model_other.People;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Cách 1 : Sử dụng @ComponentScan để tìm kiếm bean trong package tương ứng
//@ComponentScan("com.example.componentscan.model")

// Nếu sử dụng @ComponentScan để tìm kiếm bean trên nhiều package
//@ComponentScan({"com.example.componentscan.model", "com.example.componentscan.model_other"})

// Cách 2 : Sử dụng scanBasePackages để tìm kiếm bean trong package tương ứng
//@SpringBootApplication(scanBasePackages = "com.example.componentscan.model")

// Nếu sử dụng scanBasePackages để tìm kiếm bean trên nhiều package
@SpringBootApplication(scanBasePackages = {"com.example.componentscan.model", "com.example.componentscan.model_other"})
public class ComponentScanApplication {

    public static void main(String[] args) {
        // Spring Boot khi chạy sẽ dò tìm toàn bộ các Class cùng cấp hoặc ở trong các package thấp hơn và tạo ra Bean từ các Class tìm thấy.
        ApplicationContext context = SpringApplication.run(ComponentScanApplication.class, args);

        // ********* Trước khi sử dụng ComponentScan *********
//        try {
//            // Lấy bean Student
//            Student student = context.getBean(Student.class);
//            System.out.println("Instance student : " + student);
//
//            // Lấy bean People
//            People people = context.getBean(People.class);
//            System.out.println("Instance people : " + people);
//
//            // Lấy bean Animal
//            Animal animal = context.getBean(Animal.class);
//            System.out.println("Instance animal : " + animal);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }

        // ********* Sau khi sử dụng ComponentScan *********
        try {
            // Lấy bean Student
            Student student = context.getBean(Student.class);
            System.out.println("Instance student : " + student);

            // Lấy bean People
            People people = context.getBean(People.class);
            System.out.println("Instance people : " + people);

            // Lấy bean Animal
            Animal animal = context.getBean(Animal.class);
            System.out.println("Instance animal : " + animal);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
