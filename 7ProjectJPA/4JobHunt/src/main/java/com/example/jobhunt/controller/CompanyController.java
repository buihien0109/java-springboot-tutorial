package com.example.jobhunt.controller;

import com.example.jobhunt.model.Company;
import com.example.jobhunt.request.CreateCompanyRequest;
import com.example.jobhunt.request.UpdateCompanyRequest;
import com.example.jobhunt.service.CompanyService;
import com.example.jobhunt.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    // Danh sách API --> View
    @GetMapping("/admin/companies")
    public String getCompanyPage(Model model) {
        model.addAttribute("companies", companyService.getAll());
        return "admin/company-list";
    }

    @GetMapping("/admin/companies/{id}")
    public String getCompanyDetailPage(Model model, @PathVariable int id) {
        model.addAttribute("company", companyService.getById(id));
        model.addAttribute("jobs", jobService.getJobsByCompanyId(id));
        return "admin/company-detail";
    }

    @GetMapping("/admin/companies/create")
    public String getCompanyCreatepage() {
        return "admin/company-create";
    }

    // Danh sách API -> JSON
    // Tạo công ty mới
    @PostMapping("/api/admin/companies")
    public ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request) {
        Company company = companyService.createCompany(request);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    // Cập nhật thông tin công ty
    @PutMapping("/api/admin/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable int id, @RequestBody UpdateCompanyRequest request) {
        Company company = companyService.updateCompany(id, request);
        return ResponseEntity.ok(company);
    }

    // Xóa công ty theo id
    @DeleteMapping("/api/admin/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    // Upload logo cho công ty
    @PostMapping("/api/admin/companies/{id}/upload-logo")
    public ResponseEntity<?> uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        String filePath = companyService.updateLogoCompany(id, file);
        return new ResponseEntity<>(filePath, HttpStatus.CREATED);
    }

    // Xem thông tin file
    @GetMapping(value = "/api/companies/{id}/files/{fileId}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileId) {
        byte[] bytes = companyService.readFile(id, fileId);
        return ResponseEntity.ok(bytes);
    }
}
