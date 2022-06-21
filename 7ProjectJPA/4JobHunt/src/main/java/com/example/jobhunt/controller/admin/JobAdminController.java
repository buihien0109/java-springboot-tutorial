package com.example.jobhunt.controller.admin;

import com.example.jobhunt.model.dto.JobDto;
import com.example.jobhunt.request.CreateJobRequest;
import com.example.jobhunt.request.UpdateJobRequest;
import com.example.jobhunt.service.admin.ApplicantAdminService;
import com.example.jobhunt.service.admin.CompanyAdminService;
import com.example.jobhunt.service.admin.JobAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class JobAdminController {
    private final CompanyAdminService companyAdminService;
    private final JobAdminService jobAdminService;
    private final ApplicantAdminService applicantAdminService;

    // Danh sách API --> View
    @GetMapping("/admin/companies/{companyId}/jobs/create")
    public String getJobCreatePage(Model model, @PathVariable int companyId) {
        model.addAttribute("company", companyAdminService.getById(companyId));
        return "admin/job-create";
    }

    @GetMapping("/admin/companies/{companyId}/jobs/{id}/{slug}")
    public String getJobDetailPage(Model model, @PathVariable int companyId, @PathVariable int id) {
        model.addAttribute("company", companyAdminService.getById(companyId));
        model.addAttribute("job", jobAdminService.getJobById(companyId, id));
        model.addAttribute("applicants", applicantAdminService.getApplicants(companyId, id));
        return "admin/job-detail";
    }

    // Danh sách API --> JSON
    // Lấy danh sách job
    @GetMapping("/api/admin/jobs")
    public ResponseEntity<?> getJobs() {
        List<JobDto> jobs = jobAdminService.getAll();
        return ResponseEntity.ok(jobs);
    }

    // Lấy danh sách job theo công ty
    @GetMapping("/api/admin/companies/{companyId}/jobs")
    public ResponseEntity<?> getJobsOfCompany(@PathVariable int companyId) {
        List<JobDto> jobs = jobAdminService.getJobsById(companyId);
        return ResponseEntity.ok(jobs);
    }

    // Lấy chi tiết job theo id
    @GetMapping("/api/admin/companies/{companyId}/jobs/{jobId}")
    public ResponseEntity<?> getJob(@PathVariable int companyId, @PathVariable int jobId) {
        JobDto jobDto = jobAdminService.getJobById(companyId, jobId);
        return ResponseEntity.ok(jobDto);
    }

    // Tạo job mới
    @PostMapping("/api/admin/companies/{companyId}/jobs")
    public ResponseEntity<?> createJob(@PathVariable int companyId, @RequestBody CreateJobRequest request) {
        JobDto jobDto = jobAdminService.createJob(companyId, request);
        return new ResponseEntity<>(jobDto, HttpStatus.CREATED);
    }

    // Cập nhật job
    @PutMapping("/api/admin/companies/{companyId}/jobs/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable int companyId, @PathVariable int jobId, @RequestBody UpdateJobRequest request) {
        JobDto jobDto = jobAdminService.updateJob(companyId, jobId, request);
        return ResponseEntity.ok(jobDto);
    }

    // Xóa Job
    @DeleteMapping("/api/admin/companies/{companyId}/jobs/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable int companyId, @PathVariable int jobId) {
        jobAdminService.deleteJob(companyId, jobId);
        return ResponseEntity.noContent().build();
    }

    // Upload ảnh cho job
    @PostMapping("/api/admin/companies/{companyId}/jobs/{jobId}/upload-file")
    public ResponseEntity<?> uploadFile(@PathVariable int companyId, @PathVariable int jobId,
                                        @ModelAttribute("file") MultipartFile file) {
        String filePath = jobAdminService.updateImageJob(companyId, jobId, file);
        return ResponseEntity.ok(filePath);
    }
}
