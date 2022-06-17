package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Integer> {
    List<Applicant> getApplicantsByJobId(int jobId);
    Optional<Applicant> findByIdAndJobId(int id, int jobId);
}
