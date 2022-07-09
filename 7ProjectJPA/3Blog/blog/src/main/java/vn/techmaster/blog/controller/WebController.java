package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.service.BlogService;
import vn.techmaster.blog.service.CommentService;

@Controller
public class WebController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String getHome(
            @RequestParam(required = false, defaultValue = "1") int page,
            Model model
    ) {
        model.addAttribute("pageInfo", blogService.getAllBlog(page));
        model.addAttribute("categories", categoryRepository.getCategoriesPopular(5));
        model.addAttribute("blogsPopular", blogService.getBlogMostPopular(3));
        return "web/index";
    }

    @GetMapping("/blogs/{id}/{slug}")
    public String getDetail(@PathVariable String id, Model model) {
        model.addAttribute("blog", blogService.getBlogDetailById(id));
        model.addAttribute("comments", commentService.getCommentsByBlogId(id));
        model.addAttribute("categories", categoryRepository.getCategoriesPopular(5));
        model.addAttribute("blogsPopular", blogService.getBlogMostPopular(3));
        return "web/detail";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "web/contact";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "web/about";
    }
}
