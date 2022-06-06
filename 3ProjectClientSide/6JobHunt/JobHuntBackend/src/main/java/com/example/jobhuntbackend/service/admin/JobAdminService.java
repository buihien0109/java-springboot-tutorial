package com.example.jobhuntbackend.service.admin;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.Job;
import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.model.mapper.JobMapper;
import com.example.jobhuntbackend.repo.CompanyRepo;
import com.example.jobhuntbackend.repo.JobRepo;
import com.example.jobhuntbackend.request.CreateJobRequest;
import com.example.jobhuntbackend.request.UpdateJobRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobAdminService {
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;

    // Lấy danh sách tất cả các job
    public List<JobDto> getAll() {
        return jobRepo.getAll().stream().map(JobMapper::toJobDto).collect(Collectors.toList());
    }

    // Lấy danh sách tất cả công việc theo id của nhà tuyển dụng
    public List<JobDto> getJobById(int companyId) {
        Company company = companyRepo.getById(companyId);

        return jobRepo.getAll().stream()
                .filter(job -> job.getCompanyId() == company.getId())
                .sorted((job1, job2) -> job2.getEndDate().compareTo(job1.getStartDate()))
                .map(JobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết job theo id
    public JobDto getJobById(int companyId, int jobId) {
        // Kiểm tra xem job có thuộc công ty hay không
        Job job = findJob(companyId, jobId);

        return JobMapper.toJobDto(job);
    }

    // Tạo job mới
    public JobDto createJob(int companyId, CreateJobRequest request) {
        // Kiểm tra companyId có tồn tại hay không
        Company company = companyRepo.getById(companyId);

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
        job.setCompanyId(company.getId());

        jobRepo.save(job);

        return JobMapper.toJobDto(job);
    }

    // Cập nhật thông tin job
    public JobDto updateJob(int companyId, int jobId, UpdateJobRequest request) {
        // Kiểm tra xem job có thuộc công ty hay không
        Job job = findJob(companyId, jobId);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCity(request.getCity());
        job.setAddress(request.getAddress());
        job.setSkills(request.getSkills());
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setSalary(request.getSalary());

        return JobMapper.toJobDto(job);
    }

    // Xóa job theo id
    public void deleteJob(int companyId, int jobId) {
        // Kiểm tra xem job có thuộc công ty hay không
        Job job = findJob(companyId, jobId);

        // Xóa job
        jobRepo.delete(job.getId());

        // TODO : Xóa danh sách những người ứng tuyển của job
    }

    // HELPER METHOD
    // Tìm kiếm job theo companyId và jobId
    public Job findJob(int companyId, int jobId) {
        // Lấy thông tin của company
        Company company = companyRepo.getById(companyId);

        // Lấy thông tin của job
        Job job = jobRepo.getById(jobId);

        // Kiểm tra job có thuộc company hay không
        if (job.getCompanyId() != company.getId()) {
            throw new NotFoundException("Công việc có id = " + jobId + " không thuộc công ty có id = " + companyId);
        }

        return job;
    }
}
