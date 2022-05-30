package com.example.configuration_bean;

public class Student {
    private String name;
    private Vehicle vehicle;

    public Student(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
