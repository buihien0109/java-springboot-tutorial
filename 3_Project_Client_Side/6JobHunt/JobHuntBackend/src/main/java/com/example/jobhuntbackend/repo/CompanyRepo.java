package com.example.jobhuntbackend.repo;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Company;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class CompanyRepo {
    private List<Company> companies;

    public CompanyRepo() {
        initCompany();
    }

    public void initCompany() {
        Random rd = new Random();
        Faker faker = new Faker();
        companies = new ArrayList<>();

        List<String> cites = Arrays.asList("Thành phố Hà Nội", "Thành phố Đà Nẵng", "Thành phố Hồ Chí Minh");

        IntStream.range(1, 7).forEach(n -> {
            Company company = new Company();
            company.setId(n);
            company.setName(faker.company().name());
            company.setLogoPath(faker.company().logo());
            company.setWebsite(faker.company().url());
            company.setEmail(faker.internet().emailAddress());
            company.setAddress(faker.address().streetAddress());
            company.setCity(cites.get(rd.nextInt(cites.size())));
            company.setDescription(faker.lorem().sentence(30));

            companies.add(company);
        });
    }

    public List<Company> getAll() {
        return companies;
    }

    public Company getById(int id) {
        Optional<Company> optionalCompany = findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        }

        throw new NotFoundException("Không tìm thấy công ty có id = " + id);
    }

    public void save(Company company) {
        companies.add(company);
    }

    public void delete(int id) {
        Optional<Company> optionalCompany = findById(id);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Không tìm thấy công ty có id = " + id);
        }

        companies.removeIf(company -> company.getId() == id);
    }

    public Optional<Company> findById(int id) {
        return companies.stream().filter(job -> job.getId() == id).findFirst();
    }
}
