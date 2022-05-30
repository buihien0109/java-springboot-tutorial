package com.example.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
    @Autowired
    private BeanA beanA;

    public BeanA getBeanA() {
        return beanA;
    }

    public void methodB() {
        System.out.println("Đây là method của Bean B");
    }
}
