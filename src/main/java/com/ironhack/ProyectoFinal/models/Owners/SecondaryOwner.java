package com.ironhack.ProyectoFinal.models.Owners;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.CreditCard;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SecondaryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long secondaryOwnerId;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "secondaryOwner")
    private List<CreditCard> creditCards = new ArrayList<>();


    public SecondaryOwner(){}


    public SecondaryOwner(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

    }

    public Long getOwnerId() {
        return secondaryOwnerId;
    }

    public void setOwnerId(Long ownerId) {
        this.secondaryOwnerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
