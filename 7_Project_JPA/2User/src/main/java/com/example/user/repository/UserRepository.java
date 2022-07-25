package com.example.user.repository;

import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where upper(u.name) like upper(concat('%', ?1, '%'))")
    List<User> findByNameContainsIgnoreCase(String name);

    Optional<User> findUserByEmail(String email);
}
