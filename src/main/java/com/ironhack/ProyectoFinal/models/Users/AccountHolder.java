package com.ironhack.ProyectoFinal.models.Users;

import com.ironhack.ProyectoFinal.models.Address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Optional;
@Entity
public class AccountHolder extends User{
    private LocalDate dateOfBirth;
    private String password;
    @Embedded
    Address primaryAddress;


    public AccountHolder(){}

    public AccountHolder(String name, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(name);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }


}
