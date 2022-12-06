package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String secretKey;

    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private SecondaryOwner secondaryOwner;
    private BigDecimal penaltyFee = new BigDecimal(40);
    private LocalDate creationDate;
    private Status status;

    public Optional<SecondaryOwner> getSecondaryOwner() {

        return Optional.ofNullable(this.secondaryOwner);
    }

    public void setSecondaryOwner(SecondaryOwner secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Account(){}

    public Account(String secretKey, BigDecimal balance, Owner owner, SecondaryOwner secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.owner = owner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Account(String secretKey, BigDecimal balance, Owner owner, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.owner = owner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Owner getPrimaryOwner() {
        return owner;
    }

    public void setPrimaryOwner(Owner owner) {
        this.owner = owner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {

        this.penaltyFee = new BigDecimal(40);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = new BigDecimal(50);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
