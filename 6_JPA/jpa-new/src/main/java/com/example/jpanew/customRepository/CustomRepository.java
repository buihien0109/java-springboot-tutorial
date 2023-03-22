package com.example.jpanew.customRepository;

import java.util.List;

public interface CustomRepository {
    List<Customer> findByNameStartsWith(String prefix);
}
