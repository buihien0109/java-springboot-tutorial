package com.example.jobhuntbackend.service;

import com.example.jobhuntbackend.model.City;
import com.example.jobhuntbackend.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private final CompanyService companyService;

    public DataService(CompanyService companyService) {
        this.companyService = companyService;
        initCompany();
    }

    // Tạo 1 số nhà tuyển dụng
    public void initCompany() {
        List<Company> companies = companyService.companies();
        companies.add(new Company(1, "Travala.com", null, "travala.com", "travala@gmail.com", City.HaNoi, "Tòa nhà Udic Complex, N04, Hoàng Đạo Thúy, Trung Hòa, Cầu Giấy", "Founded in 2017, Travala.com has grown from a small start-up to the world’s leading blockchain-based travel booking platform trusted by thousands of customers worldwide as their preferred online travel agency."));
        companies.add(new Company(2, "Doctor Anywhere", null, "doctoranywhere.com", "doctoranywhere@gmail.com", City.HoChiMinh, "81 Cao Thang, Ward 3, District 3", "Doctor Anywhere (DA) is on a mission to be the largest tech-enabled, omnichannel healthcare provider in Southeast Asia. First launched in Singapore in 2017, DA aims to bridge gaps in the healthcare ecosystem through developing innovative solutions and technologies"));
        companies.add(new Company(3, "Travala.com", null, "Travala.com", "travala@gmail.com", City.HaNoi, "Tòa nhà Udic Complex, N04, Hoàng Đạo Thúy, Trung Hòa, Cầu Giấy", "Founded in 2017, Travala.com has grown from a small start-up to the world’s leading blockchain-based travel booking platform trusted by thousands of customers worldwide as their preferred online travel agency."));
        companies.add(new Company(4, "Travala.com", null, "Travala.com", "travala@gmail.com", City.HaNoi, "Tòa nhà Udic Complex, N04, Hoàng Đạo Thúy, Trung Hòa, Cầu Giấy", "Founded in 2017, Travala.com has grown from a small start-up to the world’s leading blockchain-based travel booking platform trusted by thousands of customers worldwide as their preferred online travel agency."));
        companies.add(new Company(5, "Travala.com", null, "Travala.com", "travala@gmail.com", City.HaNoi, "Tòa nhà Udic Complex, N04, Hoàng Đạo Thúy, Trung Hòa, Cầu Giấy", "Founded in 2017, Travala.com has grown from a small start-up to the world’s leading blockchain-based travel booking platform trusted by thousands of customers worldwide as their preferred online travel agency."));
        companies.add(new Company(6, "Travala.com", null, "Travala.com", "travala@gmail.com", City.HaNoi, "Tòa nhà Udic Complex, N04, Hoàng Đạo Thúy, Trung Hòa, Cầu Giấy", "Founded in 2017, Travala.com has grown from a small start-up to the world’s leading blockchain-based travel booking platform trusted by thousands of customers worldwide as their preferred online travel agency."));
    }
}
