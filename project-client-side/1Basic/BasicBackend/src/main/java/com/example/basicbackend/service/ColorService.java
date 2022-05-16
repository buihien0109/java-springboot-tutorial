package com.example.basicbackend.service;

import com.example.basicbackend.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ColorService {
    public String generateColor(int type) {
        String color = switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRbgColor();
            default -> throw new BadRequestException("Type không hợp lệ");
        };
        return color;
    }
    // Random màu theo name
    public String randomColorName() {
        Random rd = new Random();
        String[] colors = {"black", "green", "blue", "pink", "yellow", "red"};
        int index = rd.nextInt(colors.length);
        return colors[index];
    }

    // Random mã màu HEX
    public String randomHexColor() {
        Random rd = new Random();
        String[] characters = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            int index = rd.nextInt(characters.length);
            sb.append(characters[index]);
        }
        return sb.toString();
    }

    // Random mã màu RGB
    public String randomRbgColor() {
        Random rd = new Random();
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);

        return "rgb(" + r + "," + g + "," + b + ")";
    }
}
