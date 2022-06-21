package com.example.jobhunt.model.mapper;

import com.example.jobhunt.entity.Company;
import com.example.jobhunt.model.dto.CompanyDto;
import com.example.jobhunt.repo.JobRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyMapper {

    private final JobRepo jobRepo;

    public CompanyDto toCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setLogoPath(company.getLogoPath());
        companyDto.setCity(company.getCity());

        companyDto.setNumberOfJobs(jobRepo.getJobsByCompanyId(company.getId()).size());

        return companyDto;
    }
}
