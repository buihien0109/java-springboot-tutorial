package com.example.jobhunt.utils;


import com.github.slugify.Slugify;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Utils {
    // Format price
    public static String formatSalary(int number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number) + " VNĐ";
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
        List<String> fileExtensions = Arrays.asList("jpg", "png", "jpeg", "pdf");
        return fileExtensions.contains(fileExtension.toLowerCase());
    }
}
