package com.example.jobhunt.controller.admin;

import com.example.jobhunt.entity.Applicant;
import com.example.jobhuntbackend.service.admin.ApplicantAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("admin/api/v1")
@AllArgsConstructor
public class ApplicantAdminController {
    private final ApplicantAdminService applicantAdminService;

    @GetMapping("companies/{companyId}/jobs/{jobId}/applicants")
    public List<Applicant> getApplicants(@PathVariable int companyId, @PathVariable int jobId) {
        return applicantAdminService.getApplicants(companyId, jobId);
    }

    @DeleteMapping("companies/{companyId}/jobs/{jobId}/applicants/{id}")
    public void deleteApplicant(
            @PathVariable int companyId,
            @PathVariable int jobId,
            @PathVariable int id) {
        applicantAdminService.deleteApplicant(companyId, jobId, id);
    }
}
