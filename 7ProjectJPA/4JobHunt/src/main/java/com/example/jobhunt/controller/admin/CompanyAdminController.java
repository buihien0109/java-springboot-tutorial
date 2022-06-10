package com.example.jobhunt.controller.admin;

import com.example.jobhunt.entity.Company;
import com.example.jobhuntbackend.model.dto.CompanyDto;
import com.example.jobhuntbackend.request.CreateCompanyRequest;
import com.example.jobhuntbackend.request.UpdateCompanyRequest;
import com.example.jobhuntbackend.service.admin.CompanyAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1")
@AllArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminService companyAdminService;

    @GetMapping("/companies")
    public List<CompanyDto> getCompanies() {
        return companyAdminService.getAll();
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyAdminService.getById(id);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody CreateCompanyRequest request) {
        return companyAdminService.createCompany(request);
    }

    @PutMapping("/companies/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody UpdateCompanyRequest request) {
        return companyAdminService.updateCompany(id, request);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyAdminService.deleteCompany(id);
    }

    @PostMapping("/companies/{id}/upload-logo")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadFile(@PathVariable int id,
                             @ModelAttribute("file") MultipartFile file) {
        return companyAdminService.updateLogoCompany(id, file);
    }
}
