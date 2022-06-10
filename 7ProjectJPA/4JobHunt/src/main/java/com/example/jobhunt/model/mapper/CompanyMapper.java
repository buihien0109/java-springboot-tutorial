package com.example.jobhunt.model.mapper;

import com.example.jobhunt.entity.Company;
import com.example.jobhunt.entity.Job;
import com.example.jobhunt.model.dto.CompanyDto;
import com.example.jobhuntbackend.repo.JobRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CompanyMapper {

    private final JobRepo JobRepo;

    public CompanyDto toCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setLogoPath(company.getLogoPath());
        companyDto.setCity(company.getCity());
        List<Job> jobs = JobRepo.getByCompanyId(company.getId());
        companyDto.setNumberOfJobs(jobs.size());

        return companyDto;
    }
}
