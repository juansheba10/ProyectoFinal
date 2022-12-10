package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.CreditCardRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.models.CreditCard;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    UserRepository userRepository;

    public List<CreditCard> findAllCreditCard() {
        return creditCardRepository.findAll();
    }

    public CreditCard findCreditCardById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
    }

    public CreditCard addAccount(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void addInterestRate(Long id) {
            CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe en la base de datos"));
            creditCard.applyInterestRate();
            creditCardRepository.save(creditCard);

        }
    }

