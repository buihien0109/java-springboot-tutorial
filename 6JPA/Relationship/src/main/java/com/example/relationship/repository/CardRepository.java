package com.example.relationship.repository;

import com.example.relationship.model.one_to_one.bidrection.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCode(String code);
}