package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ShopInfo;

import java.util.List;

public interface ShopService {
    void init();
    List<Product> getAll();
    List<Product> getProductDiscount(int count);
    List<Product> getProductSell(int count);
    ShopInfo getShopInfo();
}
