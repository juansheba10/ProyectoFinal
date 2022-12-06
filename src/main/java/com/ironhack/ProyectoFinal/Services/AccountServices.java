package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;


}
