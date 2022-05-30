package com.example.bean;

import com.example.bean.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BeanApplication {

//    @Bean
//    public Student getStudent() {
//        return new Student(1, "Bùi Hiên", "hien@gmail.com");
//    }

    // Nếu có nhiều bean khác nhau thì hãy đặt "name" cho nó để phân biệt
    @Bean("studentBeanPrimary")
    public Student getStudent() {
        return new Student(1, "Bùi Hiên", "hien@gmail.com");
    }

    @Bean("studentBeanSecondary")
    public Student getStudentOther() {
        return new Student(1, "Bùi Hiên", "hien@gmail.com");
    }

    @Bean
    public Random random() {
        return new Random();
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BeanApplication.class, args);

        // Lấy ra bean
//        Student std = context.getBean(Student.class);
//        System.out.println(std);

        // Lấy ra bean với tên được chỉ định
        Student std = context.getBean("studentBeanPrimary", Student.class);
        System.out.println(std);

        // Lấy ra bean với tên được chỉ định
        Student std1 = context.getBean("studentBeanSecondary", Student.class);
        System.out.println(std1);

        // Lấy bean random
        Random rd = context.getBean(Random.class);
        System.out.println(rd.nextInt(100));

        // Lấy ra tất cả các bean trong ứng dụng
        String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

}
