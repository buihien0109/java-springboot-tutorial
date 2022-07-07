package com.example.jobhunt.repo;

import com.example.jobhunt.model.Company;
import com.example.jobhunt.model.Job;
import com.example.jobhunt.exception.NotFoundException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class JobRepo  {
    private List<Job> jobs;

    private final CompanyRepo companyRepo;

    public JobRepo(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
        initJob();
    }

    public void initJob() {
        Random rd = new Random();
        Faker faker = new Faker();

        jobs = new ArrayList<>();

        List<Company> companies = companyRepo.findAll();

        List<String> allSkills = Arrays.asList("Javascript", "Java", "Golang", "PHP", "React", "AWS", "Devops", ".Net", "Vue", "Angular");

        IntStream.range(1, 20).forEach(n -> {
            // Random ra id công ty
            Company company = companies.get(rd.nextInt(companies.size()));

            // Tạo ra job
            Job job = new Job();
            job.setId(n);
            job.setTitle(faker.job().title());
            job.setDescription(faker.lorem().sentence(100));

            // Danh sách kỹ năng của công việc
            int countSkill = rd.nextInt(3) + 2;
            List<String> skills = new ArrayList<>();
            for (int i = 0; i < countSkill; i++) {
                String skillRandom = allSkills.get(rd.nextInt(allSkills.size()));
                if(!skills.contains(skillRandom)) {
                    skills.add(skillRandom);
                }
            }
            job.setSkills(skills);

            job.setSalary(rd.nextInt(35_000_000 - 15_000_000 + 1) + 15_000_000);
            job.setCompanyId(company.getId());

            jobs.add(job);
        });
    }

    public List<Job> findAll() {
        return jobs;
    }

    public List<Job> getJobsByCompanyId(int companyId) {
        Company company = companyRepo.getById(companyId);
        return jobs.stream().filter(job -> job.getCompanyId() == company.getId()).collect(Collectors.toList());
    }

    public Job getJobById(int id) {
        Optional<Job> optionalJob = findById(id);
        if (optionalJob.isPresent()) {
            return optionalJob.get();
        }

        throw new NotFoundException("Không tìm thấy công việc có id = " + id);
    }

    public Job getJobByCompanyId(int jobId, int companyId) {
        Optional<Job> jobOptional =  jobs.stream()
                .filter(job -> job.getId() == jobId & job.getCompanyId() == companyId)
                .findFirst();

        if(jobOptional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công việc");
        }
        return jobOptional.get();
    }

    public void save(Job job) {
        jobs.add(job);
    }

    public void delete(int id) {
        jobs.removeIf(job -> job.getId() == id);
    }

    public Optional<Job> findById(int id) {
        return jobs.stream().filter(job -> job.getId() == id).findFirst();
    }
}
