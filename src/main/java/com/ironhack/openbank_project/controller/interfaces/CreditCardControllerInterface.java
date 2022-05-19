package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.CreditCard;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.web.bind.annotation.PathVariable;


import java.math.BigDecimal;

public interface CreditCardControllerInterface {
    CreditCard addCreditCardAccount(CreditCard creditCard);

    CreditCard getCreditCardAccountById(Long id);

    CreditCard updateCreditCardAccount(Long id, CreditCard creditCard);

    void deleteCreditCardAccount(Long id);

    String getActualBalance(Long id);

    Money updateCreditLimit(Long id, Money newCreditLimit);

    BigDecimal updateInterestRate(Long id, BigDecimal newInterestRate);
}
