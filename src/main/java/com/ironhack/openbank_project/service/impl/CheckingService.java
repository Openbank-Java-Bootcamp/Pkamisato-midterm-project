package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.repository.CheckingRepository;
import com.ironhack.openbank_project.service.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckingService implements CheckingServiceInterface {

    @Autowired
    CheckingRepository checkingRepository;

    public Checking addChecking(Checking checking){
        return checkingRepository.save(checking);
    }

    public Checking getCheckingById(Long id) {
        return checkingRepository.findById(id).get();
    }

    public Checking updateChecking(Long id, Checking checking){
        Checking checkingFromDB = checkingRepository.findById(id).get();
        checking.setId(checkingFromDB.getId());
        return checkingRepository.save(checking);
    }

    public void deleteChecking(Long id){
        Optional<Checking> foundChecking = checkingRepository.findById(id);
        checkingRepository.delete(foundChecking.get());
    }


}
