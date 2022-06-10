package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Integer> {
}
