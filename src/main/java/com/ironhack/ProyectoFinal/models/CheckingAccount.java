package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


public class CheckingAccount extends Account{


    private BigDecimal minimunBalance;
    private BigDecimal monthlyManteinancefee;


    public CheckingAccount(String secretKey, BigDecimal balance, Owner owner, SecondaryOwner secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, Status status, BigDecimal minimunBalance, BigDecimal monthlyManteinancefee) {
        super(secretKey, balance, owner, secondaryOwner, penaltyFee, creationDate, status);
        this.minimunBalance = minimunBalance;
        this.monthlyManteinancefee = monthlyManteinancefee;
    }

    public BigDecimal getMinimunBalance() {
        return minimunBalance;
    }

    public void setMinimunBalance(BigDecimal minimunBalance) {

        this.minimunBalance = new BigDecimal(250);
    }

    public BigDecimal getMonthlyManteinancefee() {
        return monthlyManteinancefee;
    }

    public void setMonthlyManteinancefee(BigDecimal monthlyManteinancefee) {
        this.monthlyManteinancefee = new BigDecimal(12);
    }
}
