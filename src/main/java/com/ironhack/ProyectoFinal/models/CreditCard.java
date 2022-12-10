package com.ironhack.ProyectoFinal.models;


import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private User primaryOwner;

    @ManyToOne
    private User secondaryOwner;
    private BigDecimal creditLimit = new BigDecimal(100);

    private LocalDate lastInterestDate;

    private LocalDate creationDate = LocalDate.now();

    @Digits(integer = 5, fraction = 4)
    private BigDecimal interestRate = new BigDecimal("0.2");
    private BigDecimal penaltyFee = new BigDecimal(40);

    public Optional<User> getSecondaryOwner() {
        return Optional.ofNullable(secondaryOwner);
    }

    public void setSecondaryOwner(User secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }


    public CreditCard(){}

    public CreditCard(BigDecimal balance, AccountHolder primaryOwner, BigDecimal interestRate, BigDecimal penaltyFee) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    public User getPrimaryOwner() {

        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {

        this.primaryOwner = primaryOwner;
    }

    public LocalDate getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(LocalDate lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {

        if (creditLimit.compareTo(new BigDecimal("100000")) >= 0){
            creditLimit = new BigDecimal("100000");
        } else if (creditLimit.compareTo(BigDecimal.ZERO) == 0){
            creditLimit = new BigDecimal("100");
        }
        this.creditLimit = new BigDecimal("1000");
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {

        if (interestRate.compareTo(new BigDecimal("0.2")) < 0){
            this.interestRate = interestRate;
        } else if (interestRate.compareTo(new BigDecimal(BigInteger.ZERO)) == 0) {
            this.interestRate = new BigDecimal("0.1");
        } else {
            this.interestRate = new BigDecimal("0.2");
        }
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = new BigDecimal(40);
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
