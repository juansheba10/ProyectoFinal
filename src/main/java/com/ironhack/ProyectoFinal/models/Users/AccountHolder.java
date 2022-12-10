package com.ironhack.ProyectoFinal.models.Users;

import com.ironhack.ProyectoFinal.Repositories.AccountHolderRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.Address.Address;
import com.ironhack.ProyectoFinal.models.TransferMoney;
import com.ironhack.ProyectoFinal.models.TransferMoney;
import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Entity
public class AccountHolder extends User {

    private LocalDate dateOfBirth;

    @Embedded
    Address primaryAddress;



    @ManyToOne
    private Account account;

    @OneToOne
    private TransferMoney transfer;


    public AccountHolder() {
    }



    public AccountHolder(String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            dateOfBirth = LocalDate.now();
        }
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }


    @Override
    public Account getAccount() {
        return account;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }


}