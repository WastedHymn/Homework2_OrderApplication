package org.patika.entity;

import java.time.LocalDate;

public class Client extends BaseUser {
    //private Long id;
    private String name;
    private String lastName;

    public Client() {
    }

    public Client(Long id, String name, String lastName, LocalDate registrationDate) {
        super(id, registrationDate);
        this.name = name;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate=" + getRegistrationDate().toString() +
                '}';
    }
}
