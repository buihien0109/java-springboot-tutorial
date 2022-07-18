package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShopInfo {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String facebook;
    private String zalo;
    private String map;
}
