package com.example.relationship.repository;

import com.example.relationship.model.one_to_many.bidrection.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}