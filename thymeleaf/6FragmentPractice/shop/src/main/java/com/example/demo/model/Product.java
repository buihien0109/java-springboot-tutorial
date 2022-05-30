package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private int id;
    private String name; // Tên sản phẩm
    private int price; // Giá
    private int priceDiscount; // Giá khuyến mại
    private String image; // Ảnh sản phẩm
    private int sellNumber; // Số lượng bán ra
}
