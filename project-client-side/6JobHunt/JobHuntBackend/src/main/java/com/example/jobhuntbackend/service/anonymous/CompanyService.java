package com.example.jobhuntbackend.service.anonymous;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.mapper.CompanyMapper;
import com.example.jobhuntbackend.repo.CompanyRepo;
import com.example.jobhuntbackend.response.CompanyResponse;
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
    public List<CompanyResponse> getAll() {
        return companyRepo.getAll()
                .stream()
                .map(companyMapper::toCompanyResponse)
                .sorted(Comparator.comparing(CompanyResponse::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy danh sách nhà tuyển dụng hàng đầu
    // Sắp xếp theo số lượng công việc đang active giảm dần
    // Và trả về ở dạng CompanyResponse
    public List<CompanyResponse> getAll(int limit) {
        return companyRepo.getAll()
                .stream()
                .map(companyMapper::toCompanyResponse)
                .filter(companyDto -> companyDto.getNumberOfJobs() > 0)
                .limit(limit)
                .sorted(Comparator.comparing(CompanyResponse::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy chi tiết của nhà tuyển dụng theo id
    public Company getCompanyById(int id) {
        return companyRepo.getById(id);
    }
}
