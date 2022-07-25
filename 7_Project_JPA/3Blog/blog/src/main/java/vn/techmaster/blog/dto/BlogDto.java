package vn.techmaster.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private LocalDateTime createdAt;
    private int status;
    private UserDto user;
    private List<CategoryDto> categories = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserDto implements Serializable {
        private Integer id;
        private String name;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CategoryDto implements Serializable {
        private Integer id;
        private String name;
    }
}
