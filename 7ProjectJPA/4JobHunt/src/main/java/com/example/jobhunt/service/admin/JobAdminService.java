package com.example.jobhunt.service.admin;

import com.example.jobhunt.entity.Company;
import com.example.jobhunt.entity.Job;
import com.example.jobhunt.exception.NotFoundException;
import com.example.jobhunt.model.dto.JobDto;
import com.example.jobhunt.model.mapper.JobMapper;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.repo.JobRepo;
import com.example.jobhunt.request.CreateJobRequest;
import com.example.jobhunt.request.UpdateJobRequest;
import com.example.jobhunt.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobAdminService {
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;
    private final JobMapper jobMapper;
    private final FileService fileService;

    // Lấy danh sách tất cả các công việc
    public List<JobDto> getAll() {
        return jobRepo.findAll()
                .stream()
                .map(jobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả công việc theo id của nhà tuyển dụng
    public List<JobDto> getJobsById(int companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        List<Job> jobs = jobRepo.getJobsByCompanyId(company.getId());

        return jobs
                .stream()
                .map(jobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết job theo id
    public JobDto getJobById(int companyId, int jobId) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        return jobMapper.toJobDto(job);
    }

    // Tạo job mới
    public JobDto createJob(int companyId, CreateJobRequest request) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        // Tạo job mới
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setSalary(request.getSalary());
        job.setCompanyId(company.getId());

        jobRepo.save(job);

        return jobMapper.toJobDto(job);
    }

    // Cập nhật thông tin job
    public JobDto updateJob(int companyId, int jobId, UpdateJobRequest request) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setSalary(request.getSalary());

        jobRepo.save(job);

        return jobMapper.toJobDto(job);
    }

    // Xóa job theo id
    public void deleteJob(int companyId, int jobId) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        // Xóa job
        jobRepo.delete(job);
    }

    // Thay đổi ảnh mô tả
    public String updateImageJob(int companyId, int jobId, MultipartFile file) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        // B1 : Upload file
        String filePath = fileService.uploadFile(companyId, file);

        // B2 : Cập nhật lại image cho job
        job.setImage(filePath);
        jobRepo.save(job);

        // B3 : Trả về URL Path
        return filePath;
    }
}
