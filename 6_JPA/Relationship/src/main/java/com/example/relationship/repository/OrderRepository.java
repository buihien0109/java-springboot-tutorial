package com.example.relationship.repository;

import com.example.relationship.model.one_to_many.bidrection.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}