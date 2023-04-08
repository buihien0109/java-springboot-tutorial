package com.example.jpaadvenced.criteria_api.repository;

import com.example.jpaadvenced.criteria_api.entity.User;

public interface CustomUserRepository {
    User getUserByIdCustom(Integer id);
}
