package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.controller.interfaces.CreditCardControllerInterface;
import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.service.interfaces.CreditCardServicesInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api")
public class CreditCardController implements CreditCardControllerInterface{

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

    @GetMapping("/creditCardAccounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public String getActualBalance(@PathVariable (name = "id") Long id){
        Money actualBalance = creditCardServicesInterface.getBalance(id);
        return (" Actual Balance: "+ actualBalance);
    }

    @PatchMapping("/creditCardAccounts/{id}/creditLimit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Money updateCreditLimit(@PathVariable (name = "id") Long id,@RequestBody Money newCreditLimit){
        return creditCardServicesInterface.updateCreditLimit(id,newCreditLimit);
    }

    @PatchMapping("/creditCardAccounts/{id}/interestRate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BigDecimal updateInterestRate(@PathVariable (name = "id") Long id,@RequestBody BigDecimal newInterestRate){
        return creditCardServicesInterface.updateInterestRate(id, newInterestRate);
    }




}
