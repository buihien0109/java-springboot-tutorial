package vn.techmaster.blog.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogDto {
    private String id;
    private String title;
    private String description;
}
