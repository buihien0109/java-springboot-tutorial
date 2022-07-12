package vn.techmaster.blog.util;

import vn.techmaster.blog.dto.BlogDto;

import java.util.List;

public class Utils {
    public static String generateCategoryString(List<BlogDto.CategoryDto> categories) {
        if(categories.size() == 0) {
            return "";
        }

        // Lấy ra Listname của Category
        List<String> categoriesName = categories.stream().map(BlogDto.CategoryDto::getName).toList();

        // Chuyển List -> Array
        String[] itemsArray = new String[categoriesName.size()];
        itemsArray = categoriesName.toArray(itemsArray);

        // Join Array -> String
        return String.join(", ", itemsArray);
    }
}
