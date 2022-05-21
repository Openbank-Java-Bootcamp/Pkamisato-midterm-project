package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.User;


public interface AccountHolderControllerInterface {

    User saveAccountHolder(AccountHolder accountHolder);

    AccountHolder getAccountHolder(Long id);

    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    void deleteAccountHolder(Long id);

}
