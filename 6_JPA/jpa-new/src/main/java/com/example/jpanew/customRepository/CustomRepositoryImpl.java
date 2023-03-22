package com.example.jpanew.customRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomRepositoryImpl implements CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findByNameStartsWith(String prefix) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.where(cb.like(root.get("name"), prefix + "%"));
        return entityManager.createQuery(cq).getResultList();
    }
}
