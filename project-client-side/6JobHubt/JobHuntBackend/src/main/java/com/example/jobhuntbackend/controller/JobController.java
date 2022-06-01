package com.example.jobhuntbackend.controller;

import com.example.jobhuntbackend.model.Job;
import com.example.jobhuntbackend.request.CreateJobRequest;
import com.example.jobhuntbackend.request.UpdateJobRequest;
import com.example.jobhuntbackend.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/jobs")
    public List<Job> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/companies/{companyId}/jobs")
    public List<Job> getJobsOfCompany(@PathVariable int companyId) {
        return jobService.getJobs(companyId);
    }

    @GetMapping("/companies/{companyId}/jobs/{jobId}")
    public Job getJob(@PathVariable int companyId, @PathVariable int jobId) {
        return jobService.getJobById(companyId, jobId);
    }

    @PostMapping("/companies/{companyId}/jobs")
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@PathVariable int companyId, CreateJobRequest request) {
        return jobService.createJob(companyId, request);
    }

    @PutMapping("/companies/{companyId}/jobs/{jobId}")
    public Job updateJob(@PathVariable int companyId, @PathVariable int jobId, UpdateJobRequest request) {
        return jobService.updateJob(companyId, jobId, request);
    }

    @DeleteMapping("/companies/{companyId}/jobs/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable int companyId, @PathVariable int jobId) {
        jobService.deleteJob(companyId, jobId);
    }
}
