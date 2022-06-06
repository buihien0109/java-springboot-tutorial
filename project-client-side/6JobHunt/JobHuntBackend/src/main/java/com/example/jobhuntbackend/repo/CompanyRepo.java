package com.example.jobhuntbackend.repo;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Company;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class CompanyRepo {
    private List<Company> companies;

    public CompanyRepo() {
        initCompany();
    }

    public void initCompany() {
        Faker faker = new Faker();
        companies = new ArrayList<>();
        IntStream.range(1, 11).forEach(n -> {
            Company company = new Company();
            company.setId(n);
            company.setName(faker.company().name());
            company.setLogoPath(faker.company().logo());
            company.setWebsite(faker.company().url());
            company.setEmail(faker.internet().emailAddress());
            company.setAddress(faker.address().streetAddress());
            company.setCity(faker.address().city());
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

    public Company getByName(String name) {
        Optional<Company> optionalCompany = findByName(name);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        }

        throw new NotFoundException("Không tìm thấy công ty có tên = " + name);
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

    public Optional<Company> findByName(String name) {
        return companies.stream().filter(company -> company.getName().equals(name)).findFirst();
    }
}
