package com.example.jobhuntbackend.service.admin;

import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.model.dto.CompanyDto;
import com.example.jobhuntbackend.model.mapper.CompanyMapper;
import com.example.jobhuntbackend.repo.CompanyRepo;
import com.example.jobhuntbackend.request.CreateCompanyRequest;
import com.example.jobhuntbackend.request.UpdateCompanyRequest;
import com.example.jobhuntbackend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyAdminService {
    private final CompanyRepo companyRepo;
    private final FileService fileService;
    private final CompanyMapper companyMapper;

    // Lấy danh sách tất cả nhà tuyển dụng
    // Sắp xếp theo số lượng công việc giảm dần
    public List<CompanyDto> getAll() {
        return companyRepo.getAll()
                .stream()
                .map(companyMapper::toCompanyDto)
                .sorted(Comparator.comparing(CompanyDto::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy chi tiết của nhà tuyển dụng theo id
    public Company getById(int id) {
        return companyRepo.getById(id);
    }

    // Tạo nhà tuyển dụng mới
    public Company createCompany(CreateCompanyRequest request) {
        Random rd = new Random();

        Company company = new Company();
        company.setId(rd.nextInt(1000));
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setCity(request.getCity());
        company.setAddress(request.getAddress());
        company.setDescription(request.getDescription());

        companyRepo.save(company);

        return company;
    }

    // Cập nhật thông tin của nhà tuyển dụng
    public Company updateCompany(int id, UpdateCompanyRequest request) {
        Company company = companyRepo.getById(id);

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setCity(request.getCity());
        company.setAddress(request.getAddress());
        company.setDescription(request.getDescription());

        return company;
    }

    // Xóa nhà tuyển dụng
    public void deleteCompany(int id) {
        Company company = companyRepo.getById(id);
        companyRepo.delete(company.getId());
    }

    // Thay đổi logo nhà tuyển dụng
    public String updateLogoCompany(int id, MultipartFile file) {
        Company company = companyRepo.getById(id);

        // B1 : Upload file
        String filePath = fileService.uploadFile(file);

        // B2 : Cập nhật lại logoPath cho nhà tuyển dụng
        company.setLogoPath(filePath);

        // B3 : Trả về URL Path
        return filePath;
    }
}
