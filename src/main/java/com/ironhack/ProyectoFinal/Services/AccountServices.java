package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.CheckingAccountRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.CheckingAccount;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServices {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;


    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
    }

    public Account addAccount(Account account) {

        return accountRepository.save(account);
    }

    public Account updateBalance(Long id, BigDecimal newBalance) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    public void monthlyMateinanceAccountById(Long id) {
        CheckingAccount account = checkingAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
        account.applyMonthlyMateinanceFee();
        checkingAccountRepository.save(account);
        checkingAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));

    }


    public void deleteAccountById(Long id) {
        accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
        accountRepository.deleteById(id);
    }
}
