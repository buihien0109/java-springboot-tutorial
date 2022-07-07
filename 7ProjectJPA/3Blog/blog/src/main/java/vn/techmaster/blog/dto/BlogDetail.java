package vn.techmaster.blog.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class BlogDetail extends BlogInfo{
    private String content;

    public BlogDetail(String id, String title, String slug, String description, String thumbnail, String pulishedAt, Integer countComment, String authorInfo, String content) {
        super(id, title, slug, description, thumbnail, pulishedAt, countComment, authorInfo);
        this.content = content;
    }
}

