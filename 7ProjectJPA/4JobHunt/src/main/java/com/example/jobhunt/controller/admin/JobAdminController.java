package com.example.jobhunt.controller.admin;

import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.request.CreateJobRequest;
import com.example.jobhuntbackend.request.UpdateJobRequest;
import com.example.jobhuntbackend.service.admin.JobAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1")
@AllArgsConstructor
public class JobAdminController {
    private final JobAdminService jobAdminService;

    @GetMapping("/jobs")
    public List<JobDto> getJobs() {
        return jobAdminService.getAll();
    }

    @GetMapping("/companies/{companyId}/jobs")
    public List<JobDto> getJobsOfCompany(@PathVariable int companyId) {
        return jobAdminService.getJobById(companyId);
    }

    @GetMapping("/companies/{companyId}/jobs/{jobId}")
    public JobDto getJob(@PathVariable int companyId, @PathVariable int jobId) {
        return jobAdminService.getJobById(companyId, jobId);
    }

    @PostMapping("/companies/{companyId}/jobs")
    @ResponseStatus(HttpStatus.CREATED)
    public JobDto createJob(@PathVariable int companyId, CreateJobRequest request) {
        return jobAdminService.createJob(companyId, request);
    }

    @PutMapping("/companies/{companyId}/jobs/{jobId}")
    public JobDto updateJob(@PathVariable int companyId, @PathVariable int jobId, UpdateJobRequest request) {
        return jobAdminService.updateJob(companyId, jobId, request);
    }

    @DeleteMapping("/companies/{companyId}/jobs/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable int companyId, @PathVariable int jobId) {
        jobAdminService.deleteJob(companyId, jobId);
    }
}
