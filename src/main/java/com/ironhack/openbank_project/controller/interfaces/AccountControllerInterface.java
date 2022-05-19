package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.utils.Money;



public interface AccountControllerInterface {

    void sendTransferAccount(Long senderAccountId,Money transferAmount, Long recipientAccountId);

    String getActualBalance(Long id);

    void updateActualBalance(Long id, Money newBalance);
}
