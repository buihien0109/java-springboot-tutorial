package com.example.jpaadvenced.criteria_api.repository;

import com.example.jpaadvenced.criteria_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}