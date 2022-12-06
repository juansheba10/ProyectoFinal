package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class SavingAccount extends Account{
    private BigDecimal interestRate;
    private BigDecimal minimumBalance;

    public SavingAccount() {
    }

    public SavingAccount(String secretKey, BigDecimal balance, Owner owner, SecondaryOwner secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, Status status, BigDecimal interestRate, BigDecimal minimumBalance) {
        super(secretKey, balance, owner, secondaryOwner, penaltyFee, creationDate, status);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public SavingAccount(String secretKey, BigDecimal balance, Owner owner, BigDecimal penaltyFee, LocalDate creationDate, Status status, BigDecimal interestRate, BigDecimal minimumBalance) {
        super(secretKey, balance, owner, penaltyFee, creationDate, status);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {

        this.interestRate = new BigDecimal(0.0025);
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = new BigDecimal(1000);
    }
}
