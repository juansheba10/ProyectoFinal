package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;

import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class StudentChecking extends Account {


    public StudentChecking() {
    }

    public StudentChecking(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
    }
}
