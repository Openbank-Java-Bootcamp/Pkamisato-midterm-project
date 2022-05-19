package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.controller.interfaces.AccountControllerInterface;
import com.ironhack.openbank_project.service.interfaces.AccountServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController implements AccountControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;

    @GetMapping("/accounts/{id}/amount/recipientId")
    @ResponseStatus(HttpStatus.OK)
    public void sendTransferAccount(@PathVariable (name = "id") Long senderAccountId,@RequestParam(name ="transferAmount" )Money transferAmount,@RequestParam(name ="recipientAccountId" ) Long recipientAccountId){
        System.out.println(senderAccountId);
        accountServiceInterface.sendTransfer(senderAccountId,transferAmount,recipientAccountId);
    }

    @GetMapping("/accounts/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getActualBalance(@PathVariable (name = "id") Long id){
        Money actualBalance = accountServiceInterface.getBalance(id);
        return (" Actual Balance: "+ actualBalance);
    }

    @PatchMapping("/accounts/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActualBalance(@PathVariable (name = "id") Long id,@RequestBody Money newBalance){
       accountServiceInterface.updateBalance(id,newBalance);
    }















}
