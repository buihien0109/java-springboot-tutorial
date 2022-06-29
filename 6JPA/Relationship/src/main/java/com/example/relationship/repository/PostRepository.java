package com.example.relationship.repository;

import com.example.relationship.model.many_to_many.bidirection.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}