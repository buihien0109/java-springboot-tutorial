package com.example.autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bicycle") // Đặt tên cho bean
public class Bicycle implements Vehicle {
    @Override
    public void move() {
        System.out.println("Di chuyển bằng xe đạp");
    }
}
