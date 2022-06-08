package com.example.userbackend.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // Method tạo ngẫu nhiên password với độ dài chỉ định
    // Không có ký tự chữ cái, chỉ có số
    public static String generatePassword(int count) {
        return RandomStringUtils.random(count, false, true);
    }

    // Lấy đuôi của file
    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    // Kiểm tra file có nằm trong danh sách chỉ định hay không
    public static boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
