package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogDetail;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.dto.PaginationInfo;
import vn.techmaster.blog.repository.BlogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public PaginationInfo<BlogInfo> getAllBlog(int page) {
        int limit = 6;
        int offset = (page - 1) * limit;

        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();
        List<BlogInfo> blogInfos = blogs.stream()
                .skip(offset)
                .limit(6)
                .collect(Collectors.toList());

        int totalItems = blogRepository.countBlogsByStatus(1);

        return new PaginationInfo<>(blogInfos, totalItems, page);
    }

    public List<BlogInfo> getBlogMostPopular(int limit) {
        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();
        return blogs.stream()
                .sorted((a, b) -> b.getCountComment() - a.getCountComment())
                .limit(limit).toList();
    }

    public BlogDetail getBlogById(String id) {
        return blogRepository.getBlogDetailById(id);
    }
}
