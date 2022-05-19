package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.utils.Money;

public interface AccountServiceInterface {

    void sendTransfer(Long senderAccountId,Money transferAmount, Long recipientAccountId);

    Money getBalance(Long id);

    void updateBalance(Long id, Money newBalance);

}
