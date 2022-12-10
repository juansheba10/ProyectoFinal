package com.ironhack.ProyectoFinal.models.DTO;

import java.math.BigDecimal;

public class TransferDTO {

    private Long sendAccountID;
    private Long receiveAccountID;
    private BigDecimal ammount;
    private String accountOwnerName;

    public TransferDTO(Long sendAccountID, Long receiveAccountID, BigDecimal ammount, String accountOwnerName) {
        this.sendAccountID = sendAccountID;
        this.receiveAccountID = receiveAccountID;
        this.ammount = ammount;
        this.accountOwnerName = accountOwnerName;
    }

    public Long getSendAccountID() {
        return sendAccountID;
    }

    public void setSendAccountID(Long sendAccountID) {
        this.sendAccountID = sendAccountID;
    }

    public Long getReceiveAccountID() {
        return receiveAccountID;
    }

    public void setReceiveAccountID(Long receiveAccountID) {
        this.receiveAccountID = receiveAccountID;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }
}
