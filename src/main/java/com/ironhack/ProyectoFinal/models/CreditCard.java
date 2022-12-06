package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private SecondaryOwner secondaryOwner;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal penaltyFee;

    public Optional<SecondaryOwner> getSecondaryOwner() {
        return Optional.ofNullable(secondaryOwner);
    }

    public void setSecondaryOwner(SecondaryOwner secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }


    public CreditCard(){}

    public CreditCard(BigDecimal balance, Owner owner, SecondaryOwner secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate, BigDecimal penaltyFee) {
        this.balance = balance;
        this.owner = owner;
        this.secondaryOwner = secondaryOwner;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Owner owner, BigDecimal creditLimit, BigDecimal interestRate, BigDecimal penaltyFee) {
        this.balance = balance;
        this.owner = owner;
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
        this.balance = new BigDecimal(50);
    }

    public Owner getPrimaryOwner() {
        return owner;
    }

    public void setPrimaryOwner(Owner owner) {
        this.owner = owner;
    }


    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {

        this.creditLimit = new BigDecimal(100);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {

        this.interestRate = new BigDecimal("0.2");
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}
