package com.ironhack.ProyectoFinal.models.DTO;

import jakarta.validation.constraints.Digits;

import javax.crypto.SecretKey;
import java.math.BigDecimal;

public class AccountDTO {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private BigDecimal balance;
    private String secretKey;
    @Digits(integer = 20, fraction = 2)
    private BigDecimal monthlyMaintenanceFee = new BigDecimal(12);
    private BigDecimal minimumBalance = new BigDecimal(250);

    public AccountDTO(Long primaryOwnerId, BigDecimal balance, String secretKey, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance) {
        this.primaryOwnerId = primaryOwnerId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMonthlyMaintenanceFee() {

        return monthlyMaintenanceFee;
    }

    public void setMonthlyManteinanceFee(BigDecimal monthlyManteinanceFee) {
       if (monthlyManteinanceFee.compareTo(new BigDecimal("12")) > 0){
           this.monthlyMaintenanceFee = new BigDecimal(12);
       } else if (monthlyManteinanceFee.compareTo(BigDecimal.ZERO) == 0 || monthlyManteinanceFee == null) {
           this.monthlyMaintenanceFee = new BigDecimal(12);
       } else {
           this.minimumBalance = new BigDecimal(12);
       }

    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimunBalance(BigDecimal minimunBalance) {
        if(minimunBalance.compareTo(new BigDecimal("250")) > 0){
            this.minimumBalance = new BigDecimal(250);
        } else if (minimunBalance.compareTo(BigDecimal.ZERO) == 0) {
            this.minimumBalance = new BigDecimal(250);

        }
    }
}
