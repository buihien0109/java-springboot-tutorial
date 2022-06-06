package com.example.jobhuntbackend.controller;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.response.CompanyResponse;
import com.example.jobhuntbackend.service.anonymous.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/companies")
    public List<CompanyResponse> getCompanies(@RequestParam(required = false) Integer limit) {
        if(limit == null) {
            return companyService.getAll();
        }
        return companyService.getAll(limit);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }
}
