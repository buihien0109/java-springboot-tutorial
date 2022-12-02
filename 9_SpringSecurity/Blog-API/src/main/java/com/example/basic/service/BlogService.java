package com.example.basic.service;

import com.example.basic.entity.Blog;
import com.example.basic.entity.Category;
import com.example.basic.entity.User;
import com.example.basic.exception.NotFoundException;
import com.example.basic.repository.BlogRepository;
import com.example.basic.request.UpsertBlogRequest;
import com.example.basic.security.authentication.AuthenticationFacade;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final Slugify slugify;
    private final AuthenticationFacade authenticationFacade;

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });
    }

    public Blog createBlog(UpsertBlogRequest request) {
        Blog blog = new Blog();

        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());

        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
        blog.setUser(user);

        return blogRepository.save(blog);
    }

    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });

        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());

        return blogRepository.save(blog);
    }

    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found blog with id = " + id);
        });

        blogRepository.delete(blog);
    }
}
