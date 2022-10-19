package com.example.imageservice.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {
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
