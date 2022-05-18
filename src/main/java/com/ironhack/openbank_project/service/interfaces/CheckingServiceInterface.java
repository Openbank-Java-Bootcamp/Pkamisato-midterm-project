package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.Checking;

public interface CheckingServiceInterface {
    Checking addChecking(Checking checking);
    Checking getCheckingById(Long id);

    Checking updateChecking(Long id, Checking checking);

    void deleteChecking(Long id);
}
