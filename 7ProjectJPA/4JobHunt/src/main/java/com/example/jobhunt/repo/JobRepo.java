package com.example.jobhunt.repo;

import com.example.jobhunt.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

}
