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
import vn.techmaster.blog.service.CommentService;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/admin/blogs")
    public String getBlogsPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogDto());
        return "admin/blog/blog-index";
    }

    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogsPage(Model model) {
        // TODO : Về sau lấy từ trong Authentication
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

    @GetMapping("/admin/blogs/{id}/preview")
    public String getPreviewBlogsPage(@PathVariable String id, Model model) {
        // Lấy ngẫu nhiên 5 category
        model.addAttribute("categoies", categoryService.getCategoriesPopular(5));

        // Lấy ngẫu nhiên 3 bài blog
        model.addAttribute("blogsPopular", blogService.getBlogMostPopular(3));

        // Lấy ngẫu nhiên 5 comment
        model.addAttribute("comments", commentService.getComments(5));

        // Lấy chi tiết blog
        model.addAttribute("blog", blogService.getBlogDetailById(id));

        return "web/detail";
    }

    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        // TODO : Về sau lấy từ trong Authentication
        int userId = 1;

        // Tạo blog
        Blog blog = blogService.createBlog(userId, request);

        // Trả về kết quả
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }
}
