package com.example.jobhuntbackend.model;

public enum City {
    HaNoi("Hà Nội"),
    HoChiMinh("Hồ Chí Minh"),
    HaiPhong("Hải Phòng"),
    DaNang("Đà Nẵng");

    public final String label;
    City(String label) {
        this.label = label;
    }
}
