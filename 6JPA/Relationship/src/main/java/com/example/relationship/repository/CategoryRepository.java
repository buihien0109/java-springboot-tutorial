package com.example.relationship.repository;

import com.example.relationship.model.many_to_many.bidirection.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}