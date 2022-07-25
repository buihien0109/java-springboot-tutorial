package com.example.autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bus") // Đặt tên cho bean
//@Primary // Sử dụng cho bean được ưu tiên khi inject
public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Di chuyển bằng xe bus");
    }
}
