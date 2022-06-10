package com.example.jobhunt.model.mapper;

import com.example.jobhunt.entity.Job;
import com.example.jobhunt.model.dto.JobDto;
import com.example.jobhuntbackend.repo.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobMapper {

    private final ApplicantRepo applicantRepo;

    public JobDto toJobDto(Job job) {
        JobDto jobDto = new JobDto();

        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setImage(job.getImage());
        jobDto.setSkills(job.getSkills());
        jobDto.setSalary(job.getSalary());
        jobDto.setCompanyId(job.getCompanyId());
        jobDto.setNumberOfApplicant(applicantRepo.getByJobId(job.getId()).size());

        return jobDto;
    }
}
