package org.patika.dto;

public class NewCompanyDto {
    private final String companyName;
    private final String companySector;

    public NewCompanyDto(String companyName, String companySector) {
        this.companyName = companyName;
        this.companySector = companySector;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanySector() {
        return companySector;
    }
}
