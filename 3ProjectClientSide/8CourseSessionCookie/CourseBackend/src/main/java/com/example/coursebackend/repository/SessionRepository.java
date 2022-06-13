package com.example.coursebackend.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SessionRepository {
    Map<String, Object> sessions = new HashMap<>();

    public Object findByKey(String key) {
        return sessions.get(key);
    }

    public void save(String key, Object obj) {
        sessions.put(key, obj);
    }

    public void delete(String key) {
        sessions.remove(key);
    }
}
