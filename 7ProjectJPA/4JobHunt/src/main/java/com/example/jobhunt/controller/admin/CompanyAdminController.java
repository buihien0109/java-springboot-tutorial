package com.example.jobhunt.controller.admin;

import com.example.jobhunt.entity.Company;
import com.example.jobhunt.model.dto.CompanyDto;
import com.example.jobhunt.request.CreateCompanyRequest;
import com.example.jobhunt.request.UpdateCompanyRequest;
import com.example.jobhunt.service.admin.CompanyAdminService;
import com.example.jobhunt.service.admin.JobAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminService companyAdminService;
    private final JobAdminService jobAdminService;

    // Danh sách API --> View
    @GetMapping("/admin/companies")
    public String getCompanyPage(Model model) {
        model.addAttribute("companies", companyAdminService.getAll());
        return "admin/company-index";
    }

    @GetMapping("/admin/companies/{id}/{slug}")
    public String getCompanyDetailPage(Model model, @PathVariable int id) {
        model.addAttribute("company", companyAdminService.getById(id));
        model.addAttribute("jobs", jobAdminService.getJobsById(id));
        return "admin/company-detail";
    }

    @GetMapping("/admin/companies/create")
    public String getCompanyCreatepage() {
        return "admin/company-create";
    }

    // Danh sách API -> JSON
    // Lấy danh sách công ty
    @GetMapping("/api/admin/companies")
    public ResponseEntity<?> getCompanies() {
        List<CompanyDto> companys = companyAdminService.getAll();
        return ResponseEntity.ok(companys);
    }

    // Lấy thông tin của công ty theo id
    @GetMapping("/api/admin/companies/{id}")
    public ResponseEntity<?> getCompany(@PathVariable int id) {
        Company company = companyAdminService.getById(id);
        return ResponseEntity.ok(company);
    }

    // Tạo công ty mới
    @PostMapping("/api/admin/companies")
    public ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request) {
        Company company = companyAdminService.createCompany(request);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    // Cập nhật thông tin công ty
    @PutMapping("/api/admin/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable int id, @RequestBody UpdateCompanyRequest request) {
        Company company = companyAdminService.updateCompany(id, request);
        return ResponseEntity.ok(company);
    }

    // Xóa công ty theo id
    @DeleteMapping("/api/admin/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) {
        companyAdminService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    // Upload logo cho công ty
    @PostMapping("/api/admin/companies/{id}/upload-logo")
    public ResponseEntity<?> uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        String filePath = companyAdminService.updateLogoCompany(id, file);
        return new ResponseEntity<>(filePath, HttpStatus.CREATED);
    }

    // Xem thông tin file
    @GetMapping(value = "/api/companies/{id}/files/{fileId}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileId) {
        byte[] bytes = companyAdminService.readFile(id, fileId);
        return ResponseEntity.ok(bytes);
    }
}
