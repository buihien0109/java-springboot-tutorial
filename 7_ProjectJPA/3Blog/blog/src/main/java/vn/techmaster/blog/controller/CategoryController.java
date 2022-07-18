package vn.techmaster.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/admin/category")
    public String getCategoryPage() {
        return "/admin/category/category-list";
    }
}
