package com.example.jobhunt.controller;

import com.example.jobhunt.constant.Constant;
import com.example.jobhunt.model.Job;
import com.example.jobhunt.request.CreateJobRequest;
import com.example.jobhunt.request.UpdateJobRequest;
import com.example.jobhunt.service.ApplicantService;
import com.example.jobhunt.service.CompanyService;
import com.example.jobhunt.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicantService applicantService;

    // API --> View
    @GetMapping("/admin/jobs")
    public String getJobListPage(Model model) {
        model.addAttribute("jobs", jobService.getAll());
        return "admin/job-list";
    }

    @GetMapping("/admin/jobs/create")
    public String getJobCreatePage(Model model) {
        model.addAttribute("skills", Constant.skills);
        model.addAttribute("companies", companyService.getAll());
        return "admin/job-create";
    }

    @GetMapping("/admin/jobs/{id}")
    public String getJobDetailPage(Model model, @PathVariable int id) {
        model.addAttribute("skills", Constant.skills);
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("job", jobService.getJobById(id));
        model.addAttribute("applicants", applicantService.getApplicantsByJobId(id));
        return "admin/job-detail";
    }

    // API --> JSON
    @PostMapping("/api/admin/jobs")
    public ResponseEntity<?> createJob(@RequestBody CreateJobRequest request) {
        Job job = jobService.createJob(request);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable int id, @RequestBody UpdateJobRequest request) {
        Job job = jobService.updateJob(id, request);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/api/admin/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
