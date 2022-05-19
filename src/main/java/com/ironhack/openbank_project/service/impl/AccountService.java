package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.User;
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
    @Autowired
    UserRepository userRepository;


    public void sendTransfer(Long senderAccountId,Money transferAmount, Long recipientAccountId){
        Optional<Account> accountRecipientId = accountRepository.findById(recipientAccountId);
        if(accountRecipientId .isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account found with ID:"+ accountRecipientId);
        }else{
            accountRecipientId.get().receiveTransfer(transferAmount);
            String usernameSender;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                usernameSender = ((UserDetails)principal).getUsername();
            } else {
                usernameSender = principal.toString();
            }
            User userSender = userRepository.findByUsername(usernameSender);
            accountRepository.findByPrimaryOwner((AccountHolder) userSender).sendTransfer(transferAmount);
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
        }
    }
}
