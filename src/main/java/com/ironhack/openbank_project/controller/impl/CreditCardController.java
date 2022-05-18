package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.service.interfaces.CreditCardServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CreditCardController {

    @Autowired
    CreditCardServicesInterface creditCardServicesInterface;


    @PostMapping("/creditCardAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCardAccount(@RequestBody CreditCard creditCard){
        return creditCardServicesInterface.addCreditCard(creditCard);
    }
    @GetMapping("/creditCardAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountById(@PathVariable(name = "id") Long id){
        return creditCardServicesInterface.getCreditCardById(id);
    }
    @PutMapping("/creditCardAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreditCard updateCreditCardAccount(@PathVariable(name = "id") Long id,@RequestBody CreditCard creditCard){
        return creditCardServicesInterface.updateCreditCard(id,creditCard);
    }

    @DeleteMapping("/creditCardAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditCardAccount(@PathVariable (name = "id") Long id){
        creditCardServicesInterface.deleteCreditCard(id);
    }
}
