package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class StudentChecking extends Account {


    public StudentChecking() {
    }

    public StudentChecking(String secretKey, BigDecimal balance, Owner owner, SecondaryOwner secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
        super(secretKey, balance, owner,secondaryOwner, penaltyFee, creationDate, status);

    }


}
