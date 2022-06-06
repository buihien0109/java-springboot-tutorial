package com.example.jobhuntbackend.model.mapper;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.dto.JobDto;
import com.example.jobhuntbackend.response.CompanyResponse;
import com.example.jobhuntbackend.service.anonymous.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class CompanyMapper {

    private final JobService jobService;

    public CompanyResponse toCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(company.getId());
        companyResponse.setName(company.getName());
        companyResponse.setLogoPath(company.getLogoPath());
        companyResponse.setCity(company.getCity());

        // Lấy số lượng công việc đang trong thời gian ứng tuyển của công ty
        // job.getEndDate().isAfter(LocalDate.now())
        List<JobDto> jobs = jobService.getJobs(company.getId());
        long numberOfJobs = jobs.stream()
                .map(JobMapper::toJob)
                .filter(job -> job.getEndDate().isAfter(LocalDate.now()))
                .count();

        System.out.println(numberOfJobs);

        companyResponse.setNumberOfJobs((int) numberOfJobs);

        return companyResponse;
    }
}
