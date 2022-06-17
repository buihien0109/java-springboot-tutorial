package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {
    List<Job> getJobsByCompanyId(int companyId);

    Optional<Job> findByIdAndCompanyId(int id, int companyId);
}
