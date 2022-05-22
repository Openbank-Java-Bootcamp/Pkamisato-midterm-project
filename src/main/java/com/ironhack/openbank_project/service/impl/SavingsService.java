package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.repository.SavingsRepository;
import com.ironhack.openbank_project.service.interfaces.SavingsServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;

    public Savings addSavings(Savings savings){
        return savingsRepository.save(savings);
    }
    public Savings getSavingsById(Long id){
        return savingsRepository.findById(id).get();
    }

    public void deleteSavings(Long id){
        Optional<Savings> foundSavings = savingsRepository.findById(id);
        if(foundSavings.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Savings Account found with ID:"+ id);
        }else{
        savingsRepository.delete(foundSavings.get());
        }
    }

    public Money getActualBalance(Long id){
        Optional<Savings> savingsFromDb = savingsRepository.findById(id);
        if(savingsFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Savings Account found with ID:"+ id);
        }else{
            savingsFromDb.get().addInterestRate();
            savingsFromDb.get().applyPenaltyFee();
            savingsRepository.save(savingsFromDb.get());
            return savingsFromDb.get().getBalance();
        }
    }

    public void setMinimumBalance(Long id, Money newMinimumBalance){
        Optional<Savings> savingsFromDb = savingsRepository.findById((id));
        if(savingsFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Savings Account found with ID:"+ id);
        }else{
            savingsFromDb.get().setMinimumBalance(newMinimumBalance);
            savingsRepository.save(savingsFromDb.get());
        }
    }

    public BigDecimal setInterestRate(Long id, BigDecimal newInterestRate){
        Optional<Savings> savingsFromDb = savingsRepository.findById((id));
        if(savingsFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Savings Account found with ID:"+ id);
        }else{
            savingsFromDb.get().setInterestRate(newInterestRate);
            savingsRepository.save(savingsFromDb.get());
            return savingsFromDb.get().getInterestRate();
        }
    }


}
