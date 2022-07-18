package com.example.blogbackend.model.mapper;

import com.example.blogbackend.model.Blog;
import com.example.blogbackend.model.dto.UserDto;
import com.example.blogbackend.model.response.BlogResponse;
import com.example.blogbackend.service.admin.UserAdminService;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    private final UserAdminService userAdminService;

    public BlogMapper(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    public BlogResponse toBlogResponse(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setId(blog.getId());
        blogResponse.setTitle(blog.getTitle());
        blogResponse.setContent(blog.getContent());
        blogResponse.setDescription(blog.getDescription());
        blogResponse.setSlug(blog.getSlug());
        blogResponse.setImage(blog.getImage());
        blogResponse.setStatus(blog.isStatus());
        blogResponse.setPublishedAt(blog.getPublishedAt());
        blogResponse.setCreatedAt(blog.getCreatedAt());
        blogResponse.setAuthorId(blog.getAuthorId());

        UserDto userDto = userAdminService.getUserById(blog.getAuthorId());
        blogResponse.setAuthorName(userDto.getName());

        return blogResponse;
    }
}

