package com.example.jobhuntbackend.controller;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.request.CreateCompanyRequest;
import com.example.jobhuntbackend.request.UpdateCompanyRequest;
import com.example.jobhuntbackend.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.companies();
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(CreateCompanyRequest request) {
        return companyService.createCompany(request);
    }

    @PutMapping("/companies/{id}")
    public Company updateCompany(@PathVariable int id, UpdateCompanyRequest request) {
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }

    @PostMapping("/companies/{id}/upload-logo")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadFile(@PathVariable int id,
                                        @ModelAttribute("file") MultipartFile file) {
        return companyService.updateLogoCompany(id, file);
    }
}
