package org.patika.service;

import org.patika.dto.NewCompanyDto;
import org.patika.entity.Company;
import org.patika.repository.CompanyRepository;

import java.util.List;

public class CompanyService implements Service {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.getById(id);
    }

    public String getCompanyNameByCompanyId(Long id){
        return companyRepository.getCompanyNameById(id);
    }
    public void addCompany(NewCompanyDto companyDto) {
        String companyInfo = companyDto.getCompanyName() + " " + companyDto.getCompanySector();
        boolean doesCompanyExist = companyRepository.checkCompany(companyInfo);
        if (!doesCompanyExist) {
            Company newCompany = new Company(
                    CompanyRepository.getIdCounter(),
                    companyDto.getCompanyName(),
                    companyDto.getCompanySector()
            );
            companyRepository.addCompany(newCompany);
            System.out.println(newCompany + " added.");
            return;
        }
        System.out.println("Company already exists.");
    }
}
