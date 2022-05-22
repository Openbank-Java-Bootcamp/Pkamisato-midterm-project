package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.DTO.TransferDTO;
import com.ironhack.openbank_project.controller.interfaces.AccountControllerInterface;
import com.ironhack.openbank_project.service.interfaces.AccountServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController implements AccountControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;

    @Override
    @PatchMapping("/accounts/{id}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendTransferAccount(@PathVariable (name = "id") Long senderAccountId, @RequestBody TransferDTO transferDTO){
        System.out.println(senderAccountId);
        accountServiceInterface.sendTransfer(senderAccountId,transferDTO);
    }

    @Override
    @GetMapping("/accounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public String getActualBalance(@PathVariable (name = "id") Long id){
        Money actualBalance = accountServiceInterface.getBalance(id);
        return (" Current Balance: "+ actualBalance);
    }

    @PatchMapping("/accounts/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActualBalance(@PathVariable (name = "id") Long id,@RequestBody Money newBalance){
       accountServiceInterface.updateBalance(id,newBalance);
    }


}
