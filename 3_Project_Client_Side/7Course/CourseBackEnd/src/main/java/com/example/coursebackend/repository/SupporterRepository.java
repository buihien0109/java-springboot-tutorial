package com.example.coursebackend.repository;

import com.example.coursebackend.database.FakeDB;
import com.example.coursebackend.model.Supporter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SupporterRepository {
    public Optional<Supporter> findById(int id) {
        return FakeDB.supporters
                .stream()
                .filter(supporter -> supporter.getId() == id)
                .findFirst();
    }
}
