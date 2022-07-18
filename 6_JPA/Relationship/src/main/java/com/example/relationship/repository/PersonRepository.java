package com.example.relationship.repository;

import com.example.relationship.model.one_to_many.unidrection.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}