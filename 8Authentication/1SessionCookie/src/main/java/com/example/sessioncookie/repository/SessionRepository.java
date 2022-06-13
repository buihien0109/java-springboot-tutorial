package com.example.sessioncookie.repository;

import com.example.sessioncookie.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionRepository {
    Map<String, User> sessions = new HashMap<>();

    public User findByKey(String key) {
        return sessions.get(key);
    }

    public void save(String key, User user) {
        sessions.put(key, user);
    }

    public void delete(String key) {
        sessions.remove(key);
    }
}
