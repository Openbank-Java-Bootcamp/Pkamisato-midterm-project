package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.DTO.TransferDTO;
import com.ironhack.openbank_project.model.Account;

import com.ironhack.openbank_project.repository.AccountRepository;
import com.ironhack.openbank_project.repository.UserRepository;
import com.ironhack.openbank_project.service.interfaces.AccountServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;




    public void sendTransfer(Long senderAccountId, TransferDTO transferDTO){
        //get the logged user
        String usernameSender;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            usernameSender = ((UserDetails)principal).getUsername();
        } else {
            usernameSender = principal.toString();
            System.out.println("---->"+usernameSender);
        }
        //validation logged user = owner
        String primaryOwnerName = accountRepository.findById(senderAccountId).get().getPrimaryOwner().getUsername();
        String secondaryOwnerName = null;
        if(accountRepository.findById(senderAccountId).get().getSecondaryOwner() != null){
            secondaryOwnerName= accountRepository.findById(senderAccountId).get().getSecondaryOwner().getUsername();
        }
        if(primaryOwnerName.equals(usernameSender) || ( secondaryOwnerName != null && secondaryOwnerName.equals(usernameSender))){
            //transfer
            accountRepository.findById(senderAccountId).get().receiveTransfer(transferDTO.getTransferAmount());
            accountRepository.findById(transferDTO.getRecipientAccountId()).get().sendTransfer(transferDTO.getTransferAmount());

            accountRepository.save(accountRepository.findById(senderAccountId).get());
            accountRepository.save(accountRepository.findById(transferDTO.getRecipientAccountId()).get());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have authorization to perform this transaction");
        }
    }

    public Money getBalance(Long id){
        Optional<Account> accountFromDb= accountRepository.findById(id);
        if(accountFromDb .isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account found with ID:"+ accountFromDb);
        }else{
            return accountFromDb.get().getBalance();
        }
    }

    @Override
    public void updateBalance(Long id, Money newBalance) {
        Optional<Account> accountFromDb= accountRepository.findById(id);
        if(accountFromDb .isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account found with ID:"+ accountFromDb);
        }else{
            accountFromDb.get().setBalance(newBalance);
            accountRepository.save(accountFromDb.get());
        }
    }
}
