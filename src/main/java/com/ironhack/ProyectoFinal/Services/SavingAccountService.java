package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.SavingAccountRepository;
import com.ironhack.ProyectoFinal.Repositories.StudentCheckingRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class SavingAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    public SavingAccount addAccount(SavingAccount savingAccount) {

        return savingAccountRepository.save(savingAccount);
    }

    public void findAccountById(Long id) {
        SavingAccount savingAccount = savingAccountRepository.findById(id).get();
        savingAccount.applyPenaltyFee();
        savingAccountRepository.save(savingAccount);
        savingAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
    }

    public void addInterestRate(Long id) {
        SavingAccount savingAccount = savingAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
        savingAccount.applyInterestRate();
        savingAccountRepository.save(savingAccount);

    }
}
