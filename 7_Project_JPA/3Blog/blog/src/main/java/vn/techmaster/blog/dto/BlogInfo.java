package vn.techmaster.blog.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@ToString
public class BlogInfo {
    private String id;
    private String title;
    private String slug;
    private String description;
    private String thumbnail;
    private String pulishedAt;
    private Integer countComment;
    private AuthorInfo authorInfo;

    public BlogInfo(String id, String title, String slug, String description, String thumbnail, String pulishedAt, Integer countComment, String authorInfo) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.thumbnail = thumbnail;
        this.pulishedAt = pulishedAt;
        this.countComment = countComment;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.authorInfo = objectMapper.readValue(authorInfo, AuthorInfo.class);
        } catch (Exception e) {
            this.authorInfo = null;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static
    class AuthorInfo {
        private Integer id;
        private String name;
    }
}
