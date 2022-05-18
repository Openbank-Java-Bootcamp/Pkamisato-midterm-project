package com.ironhack.openbank_project.service.interfaces;


import com.ironhack.openbank_project.model.AccountHolder;


public interface AccountHolderServiceInterface {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);

    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    void deleteAccountHolder(Long id);
}
