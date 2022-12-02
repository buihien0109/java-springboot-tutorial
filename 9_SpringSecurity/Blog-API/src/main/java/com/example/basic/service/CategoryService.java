package com.example.basic.service;

import com.example.basic.entity.Category;
import com.example.basic.exception.BadRequestException;
import com.example.basic.exception.NotFoundException;
import com.example.basic.repository.CategoryRepository;
import com.example.basic.request.UpsertCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });
    }

    public Category createCategory(UpsertCategoryRequest request) {
        if(request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Name must required");
        }

        Category category = new Category();
        category.setName(request.getName());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, UpsertCategoryRequest request) {
        if(request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Name must required");
        }

        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });

        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found category with id = " + id);
        });

        categoryRepository.delete(category);
    }
}
