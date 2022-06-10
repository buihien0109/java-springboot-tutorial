package com.example.jobhuntbackend.service.anonymous;

import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.service.admin.JobAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobService {
    private final JobAdminService jobAdminService;

    // Lấy danh sách tất cả công việc đang còn trong thời gian tuyển dụng
    // Nhưng được sắp xếp theo công việc mới nhất
    public List<JobDto> getJobs() {
        return jobAdminService.getAll();
    }

    // Lấy danh sách tất cả công việc theo id của nhà tuyển dụng
    public List<JobDto> getJobs(int companyId) {
        return jobAdminService.getAll().stream()
                .filter(job -> job.getCompanyId() == companyId)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết job theo id
    public JobDto getJobById(int companyId, int jobId) {
       return jobAdminService.getJobById(companyId, jobId);
    }
}
