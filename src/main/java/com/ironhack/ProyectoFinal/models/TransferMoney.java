package com.ironhack.ProyectoFinal.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class TransferMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @ManyToOne
    @JoinColumn(name = "get_from_account_account_id")
    private Account getFromAccount;

    @ManyToOne
    @JoinColumn(name = "get_to_account_account_id")
    private Account getToAccount;

    private BigDecimal ammount;

    public TransferMoney() {
    }

    public Account getGetToAccount() {
        return getToAccount;
    }

    public void setGetToAccount(Account getToAccount) {
        this.getToAccount = getToAccount;
    }

    public Account getGetFromAccount() {
        return getFromAccount;
    }

    public void setGetFromAccount(Account getFromAccount) {
        this.getFromAccount = getFromAccount;
    }


    public TransferMoney(Account getFromAccount, Account getToAccount, BigDecimal ammount) {
        this.getFromAccount = getFromAccount;
        this.getToAccount = getToAccount;
        this.ammount = ammount;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }


}
