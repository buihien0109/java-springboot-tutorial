package com.example.basicbackend.controller;

import com.example.basicbackend.request.BmiRequest;
import com.example.basicbackend.service.BmiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BmiController {

    private final BmiService bmiService;

    @GetMapping("/bmi")
    public ResponseEntity<?> calculateBmiWithGet(@RequestParam double weight, @RequestParam double height) {
        double bmi = bmiService.calculateBmi(weight, height);
        return ResponseEntity.ok(bmi);
    }

    @PostMapping("/bmi")
    public ResponseEntity<?> calculateBmiWithPost(@RequestBody BmiRequest request) {
        double bmi = bmiService.calculateBmi(request.getWeight(), request.getHeight());
        return ResponseEntity.ok(bmi);
    }
}
