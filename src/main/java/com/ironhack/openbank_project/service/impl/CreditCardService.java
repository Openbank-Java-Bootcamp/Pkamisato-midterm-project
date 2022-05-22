package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.repository.CheckingRepository;
import com.ironhack.openbank_project.repository.CreditCardRepository;
import com.ironhack.openbank_project.service.interfaces.CreditCardServicesInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CreditCardService implements CreditCardServicesInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard addCreditCard(CreditCard creditCard){
       return creditCardRepository.save(creditCard);
    }
    public CreditCard getCreditCardById(Long id){
        return creditCardRepository.getReferenceById(id);
    }

    public void deleteCreditCard(Long id){
        Optional<CreditCard> foundCreditCard = creditCardRepository.findById(id);
        if(foundCreditCard.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Credit card Account found with ID:"+ id);
        }else{
        creditCardRepository.delete(foundCreditCard.get());
        }
    }

    public Money getBalance(Long id){
        Optional<CreditCard> creditCardFromDb = creditCardRepository.findById((id));
        if(creditCardFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Credit card Account found with ID:"+ id);
        }else{
            creditCardFromDb.get().addInterestRate();
            return creditCardFromDb.get().getBalance();
        }
    }

    public Money updateCreditLimit(Long id, Money newCreditLimit){
        Optional<CreditCard> creditCardFromDb = creditCardRepository.findById((id));
        if(creditCardFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Credit card Account found with ID:"+ id);
    }else{
            creditCardFromDb.get().setCreditLimit(newCreditLimit);
            creditCardRepository.save(creditCardFromDb.get());
            return creditCardFromDb.get().getCreditLimit();
        }
    }

    public BigDecimal updateInterestRate(Long id, BigDecimal newInterestRate){
        Optional<CreditCard> creditCardFromDb = creditCardRepository.findById((id));
        if(creditCardFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Credit card Account found with ID:"+ id);
    }else{
            creditCardFromDb.get().setInterestRate(newInterestRate);
            creditCardRepository.save(creditCardFromDb.get());
            return creditCardFromDb.get().getInterestRate();
        }
    }


}
