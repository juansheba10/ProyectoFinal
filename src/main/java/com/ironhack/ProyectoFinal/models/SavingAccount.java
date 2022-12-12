package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;

import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class SavingAccount extends Account{
    @Digits(integer = 9, fraction = 4)
    private BigDecimal interestRate = new BigDecimal("0.0025");

    @Digits(integer = 20, fraction = 2)
    private BigDecimal minimumBalance = new BigDecimal(1000);
    private LocalDate lastInterestDate;

    public SavingAccount() {
    }

    public SavingAccount(String secretKey, BigDecimal balance, AccountHolder primaryOwner, BigDecimal interestRate, BigDecimal minimumBalance) {
        super(secretKey, balance, primaryOwner);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }



    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(new BigDecimal("0.5")) >= 0){
          interestRate = new BigDecimal("0.5");
        } else if (interestRate.compareTo(BigDecimal.ZERO) == 0) {
           interestRate = new BigDecimal("0.0025");
        }

        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {

        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {

        if(minimumBalance.compareTo(new BigDecimal("100")) > 0){
            this.minimumBalance = minimumBalance;
        } else if (minimumBalance.compareTo(BigDecimal.ZERO) == 0) {
            this.minimumBalance = new BigDecimal(1000);

        } else {
            this.minimumBalance = new BigDecimal(100);
        }
    }

    public LocalDate getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(LocalDate lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }

    public void applyPenaltyFee(){

        if (getBalance().compareTo(getMinimumBalance()) < 0){
            setBalance(getBalance().subtract(getPenaltyFee()));
        }
    }

    public void applyInterestRate(){
        LocalDate date = LocalDate.now();
        LocalDate lastInterest = this.lastInterestDate;

        if (lastInterest == null){
            lastInterest = getCreationDate();
        }
        if (date.isAfter(lastInterest.plusYears(1))){
            BigDecimal interest = getBalance().multiply(interestRate);
            setBalance(getBalance().add(interest));
            this.lastInterestDate = date;
        }
    }

}
