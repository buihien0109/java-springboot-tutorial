package com.example.jobhuntbackend.service.anonymous;

import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.model.mapper.JobMapper;
import com.example.jobhuntbackend.service.admin.JobAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobService {
    private final JobAdminService jobAdminService;

    // Lấy danh sách tất cả công việc đang còn trong thời gian tuyển dụng
    // Nhưng được sắp xếp theo công việc mới nhất
    public List<JobDto> getJobs() {
        return jobAdminService.getAll().stream()
                .map(JobMapper::toJob)
                .filter(job -> job.getEndDate().isAfter(LocalDate.now()))
                .sorted((job1, job2) -> job2.getEndDate().compareTo(job1.getStartDate()))
                .map(JobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả công việc theo id của nhà tuyển dụng
    public List<JobDto> getJobs(int companyId) {
        return jobAdminService.getAll().stream()
                .map(JobMapper::toJob)
                .filter(job -> job.getCompanyId() == companyId)
                .sorted((job1, job2) -> job2.getEndDate().compareTo(job1.getStartDate()))
                .map(JobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết job theo id
    public JobDto getJobById(int companyId, int jobId) {
       return jobAdminService.getJobById(companyId, jobId);
    }
}
