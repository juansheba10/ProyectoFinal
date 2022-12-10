package com.ironhack.ProyectoFinal.models.DTO;

import java.math.BigDecimal;

public class ThirdPartyDTO {

    private BigDecimal ammount;
    private Long sendingAccountId;
    private Long receivingAccountId;

    private String hashedKey;
    private String secretKey;

    public ThirdPartyDTO(BigDecimal ammount, Long sendingAccountId, Long receivingAccountId, String hashedKey, String secretKey) {
        this.ammount = ammount;
        this.sendingAccountId = sendingAccountId;
        this.receivingAccountId = receivingAccountId;
        this.hashedKey = hashedKey;
        this.secretKey = secretKey;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public Long getSendingAccountId() {
        return sendingAccountId;
    }

    public void setSendingAccountId(Long sendingAccountId) {
        this.sendingAccountId = sendingAccountId;
    }

    public Long getReceivingAccountId() {
        return receivingAccountId;
    }

    public void setReceivingAccountId(Long receivingAccountId) {
        this.receivingAccountId = receivingAccountId;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
