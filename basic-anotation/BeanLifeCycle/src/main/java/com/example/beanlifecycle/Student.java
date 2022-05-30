package com.example.beanlifecycle;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Student {
    private int name;

    // @PostConstruct được đánh dấu trên một method duy nhất bên trong Bean.
    // IoC Container hoặc ApplicationContext sẽ gọi hàm này sau khi một Bean được tạo ra và quản lý.
    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstructor được chạy sau khi bean được tạo");
    }

    // reDestroy được đánh dấu trên một method duy nhất bên trong Bean.
    // IoC Container hoặc ApplicationContext sẽ gọi hàm này trước khi một Bean bị xóa hoặc không được quản lý nữa.
    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy được gọi trước khi bean được xóa");
    }
}
