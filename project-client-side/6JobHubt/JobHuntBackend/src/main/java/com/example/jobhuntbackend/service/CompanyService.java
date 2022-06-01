package com.example.jobhuntbackend.service;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Company;
import com.example.jobhuntbackend.request.CreateCompanyRequest;
import com.example.jobhuntbackend.request.UpdateCompanyRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CompanyService {
    private List<Company> companies = new ArrayList<>();
    private final FileService fileService;

    public CompanyService(FileService fileService) {
        this.fileService = fileService;
    }

    // Lấy danh sách nhà tuyển dụng
    public List<Company> companies() {
        return companies;
    }

    // Lấy danh sách nhà tuyển dụng hàng đầu
    // Nhà tuyển dụng hàng đầu là nhà tuyển dụng có số lượng công việc đang tìm kiếm nhiều nhất
    // Sắp xếp theo số lượng công việc giảm dần
    public List<Company> companies(String limit) {
        return companies;
    }

    // Lấy chi tiết của nhà tuyển dụng theo id
    public Company getCompanyById(int id) {
        Optional<Company> optionalCompany = findCompany(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        }
        throw new NotFoundException("Không tìm thấy công ty có id = " + id);
    }

    // Tạo nhà tuyển dụng mới
    public Company createCompany(CreateCompanyRequest request) {
        Random rd = new Random();

        Company company = new Company();
        company.setId(rd.nextInt(1000));
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setAddress(request.getAddress());
        company.setDescription(request.getDescription());

        return company;
    }

    // Cập nhật thông tin của nhà tuyển dụng
    public Company updateCompany(int id, UpdateCompanyRequest request) {
        Optional<Company> optionalCompany = findCompany(id);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        }

        Company company = optionalCompany.get();
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setAddress(request.getAddress());
        company.setDescription(request.getDescription());

        return company;
    }

    // Xóa nhà tuyển dụng
    public void deleteCompany(int id) {
        Optional<Company> optionalCompany = findCompany(id);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        }

        companies.removeIf(company -> company.getId() == id);
    }

    // Thay đổi logo nhà tuyển dụng
    public String updateLogoCompany(int id, MultipartFile file) {
        Optional<Company> optionalCompany = findCompany(id);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        }

        // B1 : Upload file
        String filePath = fileService.uploadFile(file);

        // B2 : Cập nhật lại logoPath cho nhà tuyển dụng
        optionalCompany.get().setLogoPath(filePath);

        // B3 : Trả về URL Path
        return filePath;
    }

    // HELPER METHOD
    // Tìm kiếm nhà tuyển dụng theo id
    public Optional<Company> findCompany(int id) {
        return companies.stream().filter(company -> company.getId() == id).findFirst();
    }

    // Tìm kiếm nhà tuyển dụng theo name
    public Optional<Company> findCompany(String name) {
        return companies.stream().filter(company -> company.getName().equals(name)).findFirst();
    }
}
