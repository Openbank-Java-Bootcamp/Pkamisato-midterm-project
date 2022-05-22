package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.controller.interfaces.SavingsControllerInterface;
import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.service.interfaces.SavingsServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class SavingsController implements SavingsControllerInterface {

    @Autowired
    SavingsServiceInterface savingsServiceInterface;

   @PostMapping("/savingsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavingsAccount(@RequestBody @Valid Savings savings){
       return savingsServiceInterface.addSavings(savings);
   }

   @GetMapping("/savingsAccounts/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Savings getSavingsAccountById(@PathVariable(name = "id") Long id){
       return savingsServiceInterface.getSavingsById(id);
   }

   @DeleteMapping("/savingsAccounts/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavingsAccount(Long id){
       savingsServiceInterface.deleteSavings(id);
   }

   @GetMapping("/savingsAccounts/{id}/balance")
   @ResponseStatus(HttpStatus.OK)
   public Money getActualBalance(@PathVariable (name = "id") Long id){
        return savingsServiceInterface.getActualBalance(id);
   }

   @PatchMapping("/savingsAccounts/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMinimumBalance(@PathVariable(name = "id") Long id,@RequestBody Money newMinimBalance){
       savingsServiceInterface.setMinimumBalance(id,newMinimBalance);
   }

    @PatchMapping("/savingsAccounts/{id}/interest_rate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BigDecimal updateInterestRate(@PathVariable (name = "id") Long id, @RequestBody BigDecimal newInterestRate){
        return savingsServiceInterface.setInterestRate(id, newInterestRate);
    }





}