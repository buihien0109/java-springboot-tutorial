package com.example.jobhuntbackend.service.anonymous;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.dto.CompanyDto;
import com.example.jobhuntbackend.model.mapper.CompanyMapper;
import com.example.jobhuntbackend.repo.CompanyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;

    // Lấy danh sách nhà tuyển dụng
    // Và trả về ở dạng CompanyResponse
    public List<CompanyDto> getAll() {
        return companyRepo.getAll()
                .stream()
                .map(companyMapper::toCompanyDto)
                .sorted(Comparator.comparing(CompanyDto::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy danh sách nhà tuyển dụng hàng đầu
    // Sắp xếp theo số lượng công việc đang active giảm dần
    // Và trả về ở dạng CompanyResponse
    public List<CompanyDto> getAll(int limit) {
        return companyRepo.getAll()
                .stream()
                .map(companyMapper::toCompanyDto)
                .filter(companyDto -> companyDto.getNumberOfJobs() > 0)
                .limit(limit)
                .sorted(Comparator.comparing(CompanyDto::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy chi tiết của nhà tuyển dụng theo id
    public Company getCompanyById(int id) {
        return companyRepo.getById(id);
    }
}
