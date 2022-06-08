package com.example.jobhuntbackend.repo;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Applicant;
import com.example.jobhuntbackend.model.Job;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ApplicantRepo {
    private List<Applicant> applicants;

    private final JobRepo jobRepo;

    public ApplicantRepo(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
        initApplicant();
    }

    public void initApplicant() {
        Faker faker = new Faker();
        Random rd = new Random();

        applicants = new ArrayList<>();
        List<Job> jobs = jobRepo.getAll();

        IntStream.range(1, 51).forEach(n -> {
            // Random ra 1 job bất kỳ
            Job job = jobs.get(rd.nextInt(jobs.size()));

            // Tạo ra ứng viên
            Applicant applicant = new Applicant();
            applicant.setId(n);
            applicant.setName(faker.funnyName().name());
            applicant.setDescription(faker.lorem().sentence(50));
            applicant.setJobId(job.getId());

            applicants.add(applicant);
        });
    }

    public List<Applicant> getAll() {
        return applicants;
    }

    public Applicant getById(int id) {
        Optional<Applicant> optionalApplicant = findById(id);
        if (optionalApplicant.isPresent()) {
            return optionalApplicant.get();
        }

        throw new NotFoundException("Không tìm thấy ứng viên có id = " + id);
    }

    public List<Applicant> getByJobId(int jobId) {
        return applicants.stream().filter(applicant -> applicant.getJobId() == jobId).collect(Collectors.toList());
    }

    public void delete(int id) {
        applicants.removeIf(applicant -> applicant.getId() == id);
    }

    public Optional<Applicant> findById(int id) {
        return applicants.stream().filter(applicant -> applicant.getId() == id).findFirst();
    }
}
