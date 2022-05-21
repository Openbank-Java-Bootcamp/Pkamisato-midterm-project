package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.DTO.NewCheckingDTO;
import com.ironhack.openbank_project.model.*;
import com.ironhack.openbank_project.repository.AccountHolderRepository;
import com.ironhack.openbank_project.repository.CheckingRepository;
import com.ironhack.openbank_project.repository.StudentCheckingRepository;
import com.ironhack.openbank_project.repository.UserRepository;
import com.ironhack.openbank_project.service.interfaces.CheckingServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CheckingService implements CheckingServiceInterface {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    public Account addChecking(NewCheckingDTO newCheckingDTO){
        Long newAccountHolderId = newCheckingDTO.getPrimaryOwnerId();
        Optional <AccountHolder> newAccountHolder = accountHolderRepository.findById(newAccountHolderId);
        if(newAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with ID:"+ newAccountHolderId);
        }else{
            System.out.println(newAccountHolder);
            Period period = Period.between(newAccountHolder.get().getDateOfBirth(), LocalDate.now());
            int age = period.getYears();
            if (age < 24){
                return studentCheckingRepository.save(new StudentChecking(
                        newCheckingDTO.getSecretKey(),
                        newCheckingDTO.getBalance(),
                        accountHolderRepository.findById(newCheckingDTO.getPrimaryOwnerId()).get(),
                        accountHolderRepository.findById(newCheckingDTO.getSecondaryOwnerId()).orElse(null)
                ));
            }else{
                return checkingRepository.save(new Checking(
                        newCheckingDTO.getSecretKey(),
                        newCheckingDTO.getBalance(),
                        accountHolderRepository.findById(newCheckingDTO.getPrimaryOwnerId()).get(),
                        accountHolderRepository.findById(newCheckingDTO.getSecondaryOwnerId()).orElse(null)
                ));
            }
        }

    }

    public Checking getCheckingById(Long id) {
        return checkingRepository.findById(id).get();
    }

    public void deleteChecking(Long id){
        Optional<Checking> foundChecking = checkingRepository.findById(id);
        checkingRepository.delete(foundChecking.get());
    }

    public Money getBalance(Long id){
        Optional<Checking> checkingFromDb = checkingRepository.findById(id);
        if(checkingFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Checking Account found with ID:"+ id);
        }else{
            checkingFromDb.get().deductMonthlyMaintenanceFee();
            checkingRepository.save(checkingFromDb.get());
            return checkingFromDb.get().getBalance();
        }
    }

}
