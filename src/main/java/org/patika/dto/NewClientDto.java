package org.patika.dto;

import java.time.LocalDate;

public class NewClientDto {
    private String name;
    private String lastName;
    private LocalDate recordDate;

    public NewClientDto() {
    }

    public NewClientDto(String name, String lastName, LocalDate recordDate) {
        this.name = name;
        this.lastName = lastName;
        this.recordDate = recordDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

}
