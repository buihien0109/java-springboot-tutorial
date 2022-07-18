package vn.techmaster.blog.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface CommentInfo {
    Integer getId();

    String getContent();

    LocalDateTime getCreatedAt();

    UserInfo getUser();

    interface UserInfo {
        Integer getId();

        String getName();

        String getAvatar();
    }
}
