package com.ironhack.ProyectoFinal.Controllers;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.ThirdPartyRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.Services.AccountServices;
import com.ironhack.ProyectoFinal.Services.CheckingAccountService;
import com.ironhack.ProyectoFinal.Services.SavingAccountService;
import com.ironhack.ProyectoFinal.Services.UserService;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.DTO.ThirdPartyDTO;
import com.ironhack.ProyectoFinal.models.DTO.TransferDTO;
import com.ironhack.ProyectoFinal.models.TransferMoney;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.Admin;
import com.ironhack.ProyectoFinal.models.Users.ThirdParty;
import com.ironhack.ProyectoFinal.models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SavingAccountService savingAccountService;

    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    AccountServices accountServices;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    @GetMapping("/admin/user/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("admin/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long id){

        return userService.findUserById(id);
    }

    @GetMapping("admin/hashedkey/{hashedKey}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdParty findByHashedKey(@PathVariable String hashedKey) throws NoSuchAlgorithmException {
        return userService.findByHashedKey(hashedKey);
    }

    @PostMapping("admin/account-holder/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody AccountHolder accountHolder){
        String encodedPassword = passwordEncoder.encode(accountHolder.getPassword());
        accountHolder.setPassword(encodedPassword);
        accountHolder = userService.addUser(accountHolder);
        return accountHolder;
    }

    @GetMapping("user/user-details/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User getUserDetails(@AuthenticationPrincipal UserDetails userDetails){
        return userRepository.findByUsername(userDetails.getUsername()).get();
    }

    @PostMapping("admin/user-thirdparty/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addThirdParty(@RequestBody ThirdParty thirdParty){
        String encodedPassword = passwordEncoder.encode(thirdParty.getPassword());
        thirdParty.setPassword(encodedPassword);
        thirdParty = userService.addThirdParty(thirdParty);
        return thirdParty;
    }

    @GetMapping("user/balance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal findBalance(@PathVariable Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
        return  account.getBalance();
    }

    @PostMapping("/admin/balance/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account updateBalance(@PathVariable Long id, @RequestParam BigDecimal newBalance){
        return accountServices.updateBalance(id, newBalance);
    }


    @PostMapping("/admin/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addAdmin(@RequestBody Admin admin){
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        admin = userService.addAdmin(admin);
        return admin;
    }

    @PostMapping("/user/holder/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferMoney transferMoney(@RequestBody TransferDTO transferDTO){
        savingAccountService.findAccountById(transferDTO.getSendAccountID());
        return userService.transferMoney(transferDTO);

    }

    @DeleteMapping("/admin/delete-user/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PostMapping("/admin/thirdparty/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferMoney transferMoney(@RequestHeader String hashedKey, @RequestBody ThirdPartyDTO thirdPartyDTO) throws NoSuchAlgorithmException {
        return userService.thirdPartyTransferMoney(hashedKey,thirdPartyDTO);
    }

}
