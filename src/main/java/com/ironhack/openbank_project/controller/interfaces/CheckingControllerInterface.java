package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.DTO.NewCheckingDTO;
import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.utils.Money;


public interface CheckingControllerInterface {
    Account saveCheckingAccount(NewCheckingDTO newCheckingDTO);

    Checking getCheckingAccountById(Long id);

    void deleteCheckingAccount(Long id);

    String getActualBalance(Long id);

}
