package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.repository.AccountHolderRepository;
import com.ironhack.openbank_project.service.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;


    public AccountHolder addAccountHolder (AccountHolder accountHolder){
        System.out.println(accountHolder.getRoles());
        return accountHolderRepository.save(accountHolder);
    }

    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).get();
    }

    public AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder){
        AccountHolder accountHolderFromDB = accountHolderRepository.findById(id).get();
        accountHolder.setId(accountHolderFromDB.getId());
        return accountHolderRepository.save(accountHolder);
    }

    public void deleteAccountHolder(Long id){
        Optional<AccountHolder> foundAccountHolder= accountHolderRepository.findById(id);
        accountHolderRepository.delete(foundAccountHolder.get());
    }

}
