package com.example.dtomapper.repository;

import com.example.dtomapper.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("select a.id, a.name, a.legs from Animal a where a.name = :name")
    AnimalInfo findByName(@Param("name") String name);
}