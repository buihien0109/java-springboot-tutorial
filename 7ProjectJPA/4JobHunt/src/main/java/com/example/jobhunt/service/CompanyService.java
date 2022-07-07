package com.example.jobhunt.service;

import com.example.jobhunt.dto.CompanyInfo;
import com.example.jobhunt.model.Company;
import com.example.jobhunt.exception.NotFoundException;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.repo.JobRepo;
import com.example.jobhunt.request.CreateCompanyRequest;
import com.example.jobhunt.request.UpdateCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private FileService fileService;

    // Lấy danh sách tất cả nhà tuyển dụng
    // Sắp xếp theo số lượng công việc giảm dần
    public List<Company> getAll() {
        return companyRepo.findAll();
    }

    public List<CompanyInfo> getAllCompanyInfo() {
        List<Company> companies = companyRepo.findAll();

        return companies.stream()
                .map(company -> {
                    int numberJob = jobRepo.getJobsByCompanyId(company.getId()).size();
                    CompanyInfo companyInfo = new CompanyInfo();
                    companyInfo.setId(company.getId());
                    companyInfo.setName(company.getName());
                    companyInfo.setLogoPath(company.getLogoPath());
                    companyInfo.setCity(company.getCity());
                    companyInfo.setNumberJob(numberJob);

                    return companyInfo;
                }).collect(Collectors.toList());
    }

    public List<CompanyInfo> getTopConpanyInfo(int limit) {
        List<CompanyInfo> companies = getAllCompanyInfo();
        return companies.stream()
                .sorted((a, b) -> b.getNumberJob() - a.getNumberJob())
                .limit(limit)
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
        company.setId(rd.nextInt(100));
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setCity(request.getCity());
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
        String filePath = fileService.uploadFile(id, file);

        // B2 : Cập nhật lại logoPath cho nhà tuyển dụng
        company.setLogoPath(filePath);

        // B3 : Trả về URL Path
        return filePath;
    }

    public byte[] readFile(int id, String fileId) {
        return fileService.readFile(id, fileId);
    }
}
