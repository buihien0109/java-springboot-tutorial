package com.example.jobhunt.controller;

import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.service.anonymous.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/jobs")
    public List<JobDto> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/companies/{companyId}/jobs")
    public List<JobDto> getJobsOfCompany(@PathVariable int companyId) {
        return jobService.getJobs(companyId);
    }

    @GetMapping("/companies/{companyId}/jobs/{jobId}")
    public JobDto getJob(@PathVariable int companyId, @PathVariable int jobId) {
        return jobService.getJobById(companyId, jobId);
    }
}
