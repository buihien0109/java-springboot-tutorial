package com.example.jobhunt.repo;

import com.example.jobhunt.model.Applicant;
import com.example.jobhunt.model.Job;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    // Khởi tạo danh sách ứng viên ban đầu
    public void initApplicant() {
        Random rd = new Random();
        Faker faker = new Faker();

        applicants = new ArrayList<>();

        // Lấy danh sách job
        List<Job> jobs = jobRepo.findAll();

        // Tạo ứng viên với job tương ứng
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

    // Lấy danh sách tất cả ứng viên theo job
    public List<Applicant> getApplicantsByJobId(int jobId) {
        return applicants
                .stream()
                .filter(applicant -> applicant.getJobId() == jobId)
                .collect(Collectors.toList());
    }

    // Lưu ứng viên
    public void save(Applicant applicant) {
        applicants.add(applicant);
    }
}
