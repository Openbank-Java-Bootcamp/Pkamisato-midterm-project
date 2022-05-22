package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.controller.interfaces.AccountHolderControllerInterface;
import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.service.interfaces.AccountHolderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;


    @PostMapping("/accountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder saveAccountHolder(@RequestBody @Valid AccountHolder accountHolder){
        System.out.println(accountHolder);
        return accountHolderServiceInterface.addAccountHolder(accountHolder);
    }

    @GetMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolder(@PathVariable (name = "id") Long id){
        return accountHolderServiceInterface.getAccountHolderById(id);
    }

    @PutMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AccountHolder updateAccountHolder(@PathVariable(name = "id") Long id,@RequestBody @Valid AccountHolder accountHolder){
        return accountHolderServiceInterface.updateAccountHolder(id, accountHolder);
    }

    @DeleteMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHolder(@PathVariable(name = "id") Long id){
        accountHolderServiceInterface.deleteAccountHolder(id);
    }

    @GetMapping("/accountHolder/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountListById(@PathVariable(name="id") Long id) {
        return accountHolderServiceInterface.getAccountList(id);
    }


}
