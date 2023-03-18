package com.example.jpanew.compositeIdGenerate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIds> {
}