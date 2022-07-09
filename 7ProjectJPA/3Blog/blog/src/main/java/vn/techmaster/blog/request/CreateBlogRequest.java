package vn.techmaster.blog.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.techmaster.blog.entity.Category;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlogRequest {
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private int status;
    private List<Integer> categories;
}
