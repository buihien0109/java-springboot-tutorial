package vn.techmaster.example.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.techmaster.example.entity.Employee;
import vn.techmaster.example.entity.Student;
import vn.techmaster.example.repository.EmployeeCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Component
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
    @Autowired
    private EntityManager em;

    @Override
    public Employee getEmployeeInfo(Long id) {
        TypedQuery<Employee> typedQuery = em.createQuery("SELECT e from Employee e where e.id = :id", Employee.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }
}
