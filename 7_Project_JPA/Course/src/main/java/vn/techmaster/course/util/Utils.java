package vn.techmaster.course.util;

import vn.techmaster.course.entity.Topic;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Utils {
    public static String generateTopicString(Set<Topic> topics) {
        if(topics.size() == 0) {
            return "";
        }

        // Lấy ra Listname của Category
        List<String> topicNames = topics.stream().map(Topic::getName).toList();

        // Chuyển List -> Array
        String[] itemsArray = new String[topicNames.size()];
        itemsArray = topicNames.toArray(itemsArray);

        // Join Array -> String
        return String.join(", ", itemsArray);
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
