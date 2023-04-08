package com.example.jpaadvenced.criteria_api.repository;

import com.example.jpaadvenced.criteria_api.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserByIdCustom(Integer id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

//        Predicate condition = builder.equal(root.get(User_.ID), id);

//        query.select(root).where(condition);
//
//        return em.createQuery(query).getSingleResult();

        return null;
    }
}
