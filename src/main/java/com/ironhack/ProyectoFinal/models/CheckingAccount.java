package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class CheckingAccount extends Account{


    @Digits(integer = 20, fraction = 2)
    private BigDecimal minimumBalance = new BigDecimal(250);
    private BigDecimal monthlyManteinanceFee = new BigDecimal(12);


    public CheckingAccount() {
    }

    public CheckingAccount(String secretKey, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal monthlyManteinanceFee) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.monthlyManteinanceFee = monthlyManteinanceFee;
    }

    public void applyPenaltyFee(){

        if (getBalance().compareTo(getMinimunBalance()) < 0){
            setBalance(getBalance().subtract(getPenaltyFee()));
        }
    }


    public BigDecimal getMinimunBalance() {

        return minimumBalance;
    }

    public void setMinimunBalance(BigDecimal minimunBalance) {
        if(minimunBalance.compareTo(new BigDecimal("250")) > 0){
            this.minimumBalance = minimunBalance;
        } else if (minimunBalance.compareTo(BigDecimal.ZERO) == 0) {
            this.minimumBalance = new BigDecimal(250);

        } else {
            this.minimumBalance = new BigDecimal(100);
        }
    }

    public BigDecimal getMonthlyManteinanceFee() {

        return monthlyManteinanceFee;
    }

    public void setMonthlyManteinanceFee(BigDecimal monthlyManteinancefee) {
       if (monthlyManteinancefee.compareTo(BigDecimal.ZERO) == 0){
           this.monthlyManteinanceFee = new BigDecimal(250);
       }
       this.monthlyManteinanceFee = monthlyManteinancefee;
    }

    public void applyMonthlyMateinanceFee(){
        LocalDate date = LocalDate.now();
        LocalDate lastMonthlyFee = null;

        if (lastMonthlyFee == null){
            lastMonthlyFee = getCreationDate();
        }
        if (date.isAfter(lastMonthlyFee.plusMonths(1))){
            BigDecimal fee = getMonthlyManteinanceFee();
            setBalance(getBalance().subtract(fee));
            lastMonthlyFee = date;
        }
    }

}
