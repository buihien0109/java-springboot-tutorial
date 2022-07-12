package com.example.jobhunt.controller;

import com.example.jobhunt.request.CreateApplicantRequest;
import com.example.jobhunt.service.CompanyService;
import com.example.jobhunt.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;


    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("companies", companyService.getTopCompanyInfo(4));
        return "web/index";
    }

    @GetMapping("/nha-tuyen-dung")
    public String getCompanyListPage(Model model) {
        model.addAttribute("companies", companyService.getAllCompanyInfo());
        return "web/company-list";
    }

    @GetMapping("/nha-tuyen-dung/{id}")
    public String getCompanyDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("company", companyService.getById(id));
        model.addAttribute("jobs", jobService.getJobsByCompanyId(id));
        return "web/company-detail";
    }

    @GetMapping("/viec-lam")
    public String getJobListPage(Model model,
                                 @RequestParam(required = false) Optional<String> skill,
                                 @RequestParam(required = false) Optional<String> city) {
        model.addAttribute("jobs", jobService.getAll(skill, city));
        return "web/job-list";
    }

    @GetMapping("/viec-lam/{id}")
    public String getJobDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("job", jobService.getJobInfoById(id));
        return "web/job-detail";
    }

    @GetMapping("/viec-lam/{id}/ung-tuyen")
    public String getApplicantPage(@PathVariable int id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        return "web/apply";
    }

    @PostMapping(value = "/api/viec-lam/{id}/ung-tuyen", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> applyJob(
            @PathVariable int id,
            @RequestPart("applicant") String applicant,
            @RequestPart("file") MultipartFile file
            ) {
        jobService.applyJob(id, applicant, file);
        return ResponseEntity.noContent().build();
    }
}
