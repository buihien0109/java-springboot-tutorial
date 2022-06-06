package com.example.circulardependencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CircularDependenciesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CircularDependenciesApplication.class, args);

        // Lấy ra beanA
        BeanA beanA = context.getBean(BeanA.class);
        System.out.println("Instance BeanA = " + beanA);
        beanA.methodA();
        beanA.getBeanB().methodB();

        // Lấy ra beanB
        BeanB beanB = context.getBean(BeanB.class);
        System.out.println("Instance BeanB = " + beanB);
        beanB.methodB();
        beanB.getBeanA().methodA();
    }

}
