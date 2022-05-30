package com.example.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanA {
    @Autowired
    @Lazy
    private BeanB beanB;

    public BeanB getBeanB() {
        return beanB;
    }

    public void methodA() {
        System.out.println("Đây là method của Bean A");
    }
}