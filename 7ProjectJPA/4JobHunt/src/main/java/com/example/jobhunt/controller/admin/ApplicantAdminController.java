package com.example.jobhunt.controller.admin;

import com.example.jobhunt.entity.Applicant;
import com.example.jobhunt.request.CreateApplicantRequest;
import com.example.jobhunt.service.admin.ApplicantAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ApplicantAdminController {
    private final ApplicantAdminService applicantAdminService;

    @GetMapping("/api/admin/companies/{companyId}/jobs/{jobId}/applicants")
    public List<Applicant> getApplicants(@PathVariable int companyId, @PathVariable int jobId) {
        return applicantAdminService.getApplicants(companyId, jobId);
    }

    @DeleteMapping("/api/admin/companies/{companyId}/jobs/{jobId}/applicants/{id}")
    public void deleteApplicant(
            @PathVariable int companyId,
            @PathVariable int jobId,
            @PathVariable int id) {
        applicantAdminService.deleteApplicant(companyId, jobId, id);
    }

    @PostMapping("/api/admin/companies/{companyId}/jobs/{jobId}/applicants/upload-cv")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void createApplicant(@PathVariable int companyId,
                  @PathVariable int jobId,
                  @RequestBody CreateApplicantRequest request) {
        applicantAdminService.createApplicant(companyId, jobId, request);
    }
}
