package com.example.jobhunt.service.admin;

import com.example.jobhunt.entity.Company;
import com.example.jobhunt.exception.NotFoundException;
import com.example.jobhunt.model.dto.CompanyDto;
import com.example.jobhunt.model.mapper.CompanyMapper;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.request.CreateCompanyRequest;
import com.example.jobhunt.request.UpdateCompanyRequest;
import com.example.jobhunt.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
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
        return companyRepo.findAll()
                .stream()
                .map(companyMapper::toCompanyDto)
                .sorted(Comparator.comparing(CompanyDto::getNumberOfJobs).reversed())
                .collect(Collectors.toList());
    }

    // Lấy chi tiết của nhà tuyển dụng theo id
    public Company getById(int id) {
        return companyRepo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        });
    }

    // Tạo nhà tuyển dụng mới
    public Company createCompany(CreateCompanyRequest request) {
        Company company = new Company();
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
        Company company = companyRepo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        });

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setCity(request.getCity());
        company.setAddress(request.getAddress());
        company.setDescription(request.getDescription());

        companyRepo.save(company);

        return company;
    }

    // Xóa nhà tuyển dụng
    public void deleteCompany(int id) {
        Company company = companyRepo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        });
        companyRepo.delete(company);
    }

    // Thay đổi logo nhà tuyển dụng
    public String updateLogoCompany(int id, MultipartFile file) {
        Company company = companyRepo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        });

        // B1 : Upload file
        String filePath = fileService.uploadFile(file);

        // B2 : Cập nhật lại logoPath cho nhà tuyển dụng
        company.setLogoPath(filePath);
        companyRepo.save(company);

        // B3 : Trả về URL Path
        return filePath;
    }
}
