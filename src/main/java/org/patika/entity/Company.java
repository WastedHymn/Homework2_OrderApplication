package org.patika.entity;

public class Company {
    private Long id;
    private String companyName;
    private String sector;

    public Company(Long id, String companyName, String sector) {
        this.id = id;
        this.companyName = companyName;
        this.sector = sector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
