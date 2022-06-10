package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
