package org.patika.entity;

import java.time.LocalDate;

public class BaseUser {
    private Long id;
    private LocalDate registrationDate;

    public BaseUser(Long id, LocalDate registrationDate) {
        this.id = id;
        this.registrationDate = registrationDate;
    }

    public BaseUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
