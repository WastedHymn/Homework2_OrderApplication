package org.patika.repository;

import org.patika.entity.Company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CompanyRepository implements Repository{
    private final static HashSet<String> companyInfos = new HashSet<>();
    private final static List<Company> companies = new ArrayList<>();
    private static Long idCounter = 1L;
    @Override
    public List<Company> getAll() {
        return companies;
    }

    @Override
    public Company getById(Long id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public String getCompanyNameById(Long id){
        Company c = getById(id);
        if(c != null){
            return c.getCompanyName();
        }
        return null;
    }

    public String getSectorById(Long id){
        return getById(id).getSector();
    }

    public boolean checkCompany(String companyInfo){
        return companyInfos.contains(companyInfo);
    }

    public void addCompany(Company company){
        companies.add(company);
        companyInfos.add(company.getCompanyName() + " " + company.getSector());
        idCounter++;
    }

    public static Long getIdCounter() {
        return idCounter;
    }
}
