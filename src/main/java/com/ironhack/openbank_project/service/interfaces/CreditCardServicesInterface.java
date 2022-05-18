package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.CreditCard;

public interface CreditCardServicesInterface {

    CreditCard addCreditCard(CreditCard creditCard);
    CreditCard getCreditCardById(Long id);
    CreditCard updateCreditCard(Long id, CreditCard creditCard);
    void deleteCreditCard(Long id);
}
