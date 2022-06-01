package com.example.jobhuntbackend.service;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.Job;
import com.example.jobhuntbackend.request.CreateJobRequest;
import com.example.jobhuntbackend.request.UpdateJobRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JobService {
    private List<Job> jobs;
    private final CompanyService companyService;

    // Inject qua constructor
    public JobService(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Lấy danh sách tất cả công việc đang còn trong thời gian tuyển dụng
    // Nhưng được sắp xếp theo công việc mới nhất
    public List<Job> getJobs() {
        return jobs.stream()
                .filter(job -> job.getEndDate().isAfter(LocalDate.now()))
                .sorted((job1, job2) -> job2.getEndDate().compareTo(job1.getStartDate()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả công việc theo id của nhà tuyển dụng
    public List<Job> getJobs(int companyId) {
        return jobs.stream()
                .filter(job -> job.getCompanyId() == companyId)
                .sorted((job1, job2) -> job2.getEndDate().compareTo(job1.getStartDate()))
                .collect(Collectors.toList());
    }

    // Lấy chi tiết job theo id
    public Job getJobById(int companyId, int jobId) {
        // Kiểm tra xem id
        Optional<Job> optionalJob = findJob(companyId, jobId);
        if(optionalJob.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        }

        return optionalJob.get();
    }

    // Tạo job mới
    public Job createJob(int companyId, CreateJobRequest request) {
        // Kiểm tra companyId có tồn tại hay không
        Optional<Company> optionalCompany = companyService.findCompany(companyId);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        }

        // Tạo job mới
        Random rd = new Random();
        Job job = new Job();

        job.setId(rd.nextInt(1000));
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCity(request.getCity());
        job.setAddress(request.getAddress());
        job.setSkills(request.getSkills());
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setSalary(request.getSalary());
        job.setCompanyId(companyId);

        jobs.add(job);
        return job;
    }

    // Cập nhật thông tin job
    public Job updateJob(int companyId, int jobId, UpdateJobRequest request) {
        // Kiểm tra jobId có tồn tại hay không
        Optional<Job> optionalJob = findJob(companyId, jobId);
        if (optionalJob.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        }

        // Lấy ra thông tin job
        Job job = optionalJob.get();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCity(request.getCity());
        job.setAddress(request.getAddress());
        job.setSkills(request.getSkills());
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setSalary(request.getSalary());

        return job;
    }

    // Xóa job theo id
    public void deleteJob(int companyId, int jobId) {
        // Kiểm tra xem id
        Optional<Job> optionalJob = findJob(companyId, jobId);
        if(optionalJob.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        }

        jobs.removeIf(job -> job.getId() == jobId);
    }

    // HELPER METHOD
    // Tìm kiếm job theo companyId và jobId
    public Optional<Job> findJob(int companyId, int jobId) {
        // Kiểm tra nhà tuyển dụng có tồn tại hay không
        Optional<Company> optionalCompany = companyService.findCompany(companyId);
        if(optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        }

        // Kiểm tra xem có job nào có id tương ứng và cùng nhà tuyển dụng hay không
        return jobs.stream()
                .filter(job -> job.getId() == jobId && job.getCompanyId() == companyId)
                .findFirst();
    }
}
