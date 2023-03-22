package com.example.jpanew.one_to_one;

import com.example.jpanew.one_to_one.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Integer> {
}