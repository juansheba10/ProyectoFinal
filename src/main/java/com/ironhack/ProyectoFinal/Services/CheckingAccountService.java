package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.AccountHolderRepository;
import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.CheckingAccountRepository;
import com.ironhack.ProyectoFinal.Repositories.StudentCheckingRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.CheckingAccount;
import com.ironhack.ProyectoFinal.models.DTO.AccountDTO;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CheckingAccountService {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    StudentCheckingService studentCheckingService;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    AccountRepository accountRepository;

    public void findAccountById(Long id) {
        CheckingAccount checkingAccount = checkingAccountRepository.findById(id).get();
        checkingAccount.applyPenaltyFee();
        checkingAccountRepository.save(checkingAccount);


        checkingAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));


    }


    public Account addCheckingAccount(AccountDTO accountDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no se encuentra en la base de datos"));
        AccountHolder secondaryOwner = null;
        Period fromYears = Period.ofYears(24);


        if (primaryOwner.getDateOfBirth().isAfter(LocalDate.now().minusYears(24))){
            Account studentChecking = new StudentChecking(accountDTO.getSecretKey(), accountDTO.getBalance(),primaryOwner, secondaryOwner);
            return accountRepository.save(studentChecking);
        } else {
            Account checkingAccount = new CheckingAccount(accountDTO.getSecretKey(), accountDTO.getBalance(), primaryOwner, secondaryOwner, accountDTO.getMinimumBalance(), accountDTO.getMonthlyMaintenanceFee());
            return accountRepository.save(checkingAccount);
        }
    }
}
