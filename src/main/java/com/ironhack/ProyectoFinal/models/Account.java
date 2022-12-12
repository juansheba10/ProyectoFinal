package com.ironhack.ProyectoFinal.models;

import com.ironhack.ProyectoFinal.Controllers.AccountController;
import com.ironhack.ProyectoFinal.Enums.Status;

import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String secretKey;

    private BigDecimal balance = new BigDecimal(1000);
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User primaryOwner;

    @ManyToOne
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee = new BigDecimal(40);
    private LocalDate creationDate = LocalDate.now();
    private Status status = Status.ACTIVE;




    public Optional<AccountHolder> getSecondaryOwner() {

        return Optional.ofNullable(this.secondaryOwner);
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {

        this.secondaryOwner = secondaryOwner;
    }

    public Account(){}

    public Account(String secretKey, BigDecimal balance,AccountHolder primaryOwner) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
    }

    public Account(String secretKey, BigDecimal balance, AccountHolder primaryOwner, LocalDate creationDate, Status status) {
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
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

    public User getPrimaryOwner() {

        return primaryOwner;
    }

    public void setPrimaryOwner(User primaryOwner) {

        this.primaryOwner = primaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {

        this.penaltyFee = penaltyFee;
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
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }




}
