package com.ironhack.ProyectoFinal.Controllers;

import com.ironhack.ProyectoFinal.Services.CreditCardService;
import com.ironhack.ProyectoFinal.models.CreditCard;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> findAllCreditCard(){
        return creditCardService.findAllCreditCard();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findCreditCardById(@PathVariable Long id){
        return creditCardService.findCreditCardById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@RequestBody CreditCard creditCard){
        return creditCardService.addAccount(creditCard);
    }

    @GetMapping("interest/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void applyInterestRate(@PathVariable Long id){

        creditCardService.addInterestRate(id);

    }

}
