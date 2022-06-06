package com.example.jobhuntbackend.model.mapper;

import com.example.jobhuntbackend.model.Job;
import com.example.jobhuntbackend.model.dto.JobDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobMapper {
    public static JobDto toJobDto(Job job) {
        JobDto jobDto = new JobDto();

        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setCity(job.getCity());
        jobDto.setAddress(job.getAddress());
        jobDto.setImage(job.getImage());
        jobDto.setSkills(job.getSkills());
        jobDto.setStartDate(job.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jobDto.setEndDate(job.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jobDto.setSalary(job.getSalary());
        jobDto.setCompanyId(job.getCompanyId());

        return jobDto;
    }

    public static Job toJob(JobDto jobDto) {
        Job job = new Job();

        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setCity(jobDto.getCity());
        job.setAddress(jobDto.getAddress());
        job.setImage(jobDto.getImage());
        job.setSkills(jobDto.getSkills());
        job.setStartDate(LocalDate.parse(jobDto.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        job.setEndDate(LocalDate.parse(jobDto.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        job.setSalary(jobDto.getSalary());
        job.setCompanyId(jobDto.getCompanyId());

        return job;
    }
}
