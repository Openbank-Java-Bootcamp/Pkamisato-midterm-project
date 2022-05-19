package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.utils.Money;

import java.math.BigDecimal;

public interface CreditCardServicesInterface {

    CreditCard addCreditCard(CreditCard creditCard);
    CreditCard getCreditCardById(Long id);
    CreditCard updateCreditCard(Long id, CreditCard creditCard);
    void deleteCreditCard(Long id);

    Money updateCreditLimit(Long id, Money newCreditLimit);

    BigDecimal updateInterestRate(Long id, BigDecimal newInterestRate);

}
