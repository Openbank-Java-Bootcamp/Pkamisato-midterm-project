package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.repository.CheckingRepository;
import com.ironhack.openbank_project.repository.CreditCardRepository;
import com.ironhack.openbank_project.service.interfaces.CreditCardServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CreditCard updateCreditCard(Long id, CreditCard creditCard){
        CreditCard creditCardFromDb = creditCardRepository.findById(id).get();
        creditCard.setId(creditCardFromDb.getId());
        return creditCardRepository.save(creditCard);
    }
    public void deleteCreditCard(Long id){
        Optional<CreditCard> foundCreditCard = creditCardRepository.findById(id);
        creditCardRepository.delete(foundCreditCard.get());
    }
}
