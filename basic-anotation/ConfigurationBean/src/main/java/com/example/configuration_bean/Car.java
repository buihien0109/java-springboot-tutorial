package com.example.configuration_bean;

public class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Di chuyển bằng ô tô");
    }
}
