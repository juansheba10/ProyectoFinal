package com.ironhack.ProyectoFinal.Controllers;

import com.ironhack.ProyectoFinal.Repositories.RoleRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.Services.AccountServices;
import com.ironhack.ProyectoFinal.Services.CheckingAccountService;
import com.ironhack.ProyectoFinal.Services.SavingAccountService;
import com.ironhack.ProyectoFinal.Services.StudentCheckingService;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.DTO.AccountDTO;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountServices accountServices;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CheckingAccountService checkingAccountService;



    @Autowired
    StudentCheckingService studentCheckingService;

    @Autowired
    SavingAccountService savingAccountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts(){
        return accountServices.findAllAccounts();
    }

    @GetMapping("/admin/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long id){

        return accountServices.findAccountById(id);
    }

    @GetMapping("/penalty/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void applyCheckingAccountPenaltyFee(@PathVariable Long id){

        checkingAccountService.findAccountById(id);
    }

    @PostMapping("/admin/saving-accounts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingAccount addSavingCount(@RequestBody SavingAccount savingAccount){

        return savingAccountService.addAccount(savingAccount);
    }

    @PostMapping("admin/accounts-add")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCount(@RequestBody Account account){
        String encodedPassword = passwordEncoder.encode(account.getSecretKey());
        account.setSecretKey(encodedPassword);
        return accountServices.addAccount(account);
    }

    @GetMapping("/admin/penalty/saving-account/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void applyPenaltyFeeSaving(@PathVariable Long id){

        savingAccountService.findAccountById(id);

    }

    @PostMapping("/admin/student-accounts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking addSavingCount(@RequestBody StudentChecking studentChecking){
        String encodedPassword = passwordEncoder.encode(studentChecking.getSecretKey());
        studentChecking.setSecretKey(encodedPassword);
        return studentCheckingService.addAccount(studentChecking);
    }

    @PostMapping("/admin/checking-accounts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCheckingCount(@RequestBody AccountDTO accountDTO){
        return  checkingAccountService.addCheckingAccount(accountDTO);
    }

    @PatchMapping("admin/interest/saving-account/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void applyInterestRate(@PathVariable Long id){

        savingAccountService.addInterestRate(id);

    }

    @DeleteMapping("/admin/delete-accounts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccountById(@PathVariable Long id){
        accountServices.deleteAccountById(id);
    }

    @PutMapping("admin/monthlyfee/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void applyPenaltyFee(@PathVariable Long id){

        accountServices.monthlyMateinanceAccountById(id);
    }

}
