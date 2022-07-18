package com.example.configuration_bean;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Di chuyển bằng xe bus");
    }
}
