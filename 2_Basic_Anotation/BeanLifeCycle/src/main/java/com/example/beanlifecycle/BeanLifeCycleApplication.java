package com.example.beanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeanLifeCycleApplication {

    public static void main(String[] args) {
        // IoC Container
        System.out.println("** Trước khi IoC Container được khởi tạo **");

        ConfigurableApplicationContext context = SpringApplication.run(BeanLifeCycleApplication.class, args);

        System.out.println("** Sau khi IoC Container được khởi tạo **");


        // Lấy ra bean Student
        Student student = context.getBean(Student.class);
        System.out.println("Instance student : " + student);

        // Xóa bean
        System.out.println("** Trước khi bean được xóa **");

        context.getBeanFactory().destroyBean(student);

        System.out.println("** Sau khi bean được xóa **");
    }

}
