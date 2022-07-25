package com.example.basicbackend.controller;

import com.example.basicbackend.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/random-color")
    @ResponseBody
    public ResponseEntity<?> randomColor(@RequestParam int type) {
        String color = colorService.generateColor(type);
        return ResponseEntity.ok(color);
    }
}
