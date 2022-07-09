package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.request.CreateBlogRequest;
import vn.techmaster.blog.service.BlogService;
import vn.techmaster.blog.service.CategoryService;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/blogs")
    public String getBlogsPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogDto());
        return "admin/blog/blog-index";
    }

    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogsPage(Model model) {
        // Về sau lấy từ trong Authentication
        int userId = 1;

        model.addAttribute("blogs", blogService.getBlogDtoByUserId(userId));
        return "admin/blog/blog-yourself";
    }

    @GetMapping("/admin/blogs/create")
    public String getCreateBlogPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/blog/blog-create";
    }

    @GetMapping("/admin/blogs/{id}/detail")
    public String getDetailBlogsPage(@PathVariable String id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("blog", blogService.getBlogById(id));
        return "admin/blog/blog-detail";
    }

    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        // Về sau lấy từ trong Authentication
        int userId = 1;

        // Tạo blog
        Blog blog = blogService.createBlog(userId, request);

        // Trả về kết quả
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }
}
