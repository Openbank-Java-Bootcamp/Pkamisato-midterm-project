package com.ironhack.openbank_project.service.interfaces;


import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.User;

import java.util.List;


public interface AccountHolderServiceInterface {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);

    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    void deleteAccountHolder(Long id);

    List<Account> getAccountList(Long id);
}
