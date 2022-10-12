package vn.techmaster.example.repository;

import vn.techmaster.example.entity.Employee;

public interface EmployeeCustomRepository {
    Employee getEmployeeInfo(Long id);
}
