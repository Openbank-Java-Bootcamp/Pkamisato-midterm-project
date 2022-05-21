package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.DTO.NewCheckingDTO;
import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.utils.Money;

public interface CheckingServiceInterface {
    Account addChecking(NewCheckingDTO newCheckingDTO);
    Checking getCheckingById(Long id);

    void deleteChecking(Long id);

    Money getBalance(Long id);
}
