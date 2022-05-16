package com.example.basicbackend.service;

import com.example.basicbackend.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    public double calculateBmi(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new BadRequestException("Chiều cao hoặc cân năng phải lớn hơn 0");
        }
        return weight / (height * height);
    }
}
