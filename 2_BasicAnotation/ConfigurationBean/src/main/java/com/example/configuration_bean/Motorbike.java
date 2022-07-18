package com.example.configuration_bean;

public class Motorbike implements Vehicle {
    @Override
    public void move() {
        System.out.println("Di chuyển bằng xe máy");
    }
}
