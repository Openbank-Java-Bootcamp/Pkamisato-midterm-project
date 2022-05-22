package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.User;
import com.ironhack.openbank_project.repository.AccountHolderRepository;
import com.ironhack.openbank_project.repository.RoleRepository;
import com.ironhack.openbank_project.service.interfaces.AccountHolderServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //help log in
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public AccountHolder addAccountHolder(AccountHolder accountHolder){
        log.info("Saving a new AccountHolder {} inside of the database",accountHolder.getName());
        accountHolder.setPassword(passwordEncoder.encode(accountHolder.getPassword()));
        return accountHolderRepository.save(accountHolder);
    }

    public AccountHolder getAccountHolderById(Long id) {
        Optional<AccountHolder> accountHolderFromDB = accountHolderRepository.findById(id);
        if(accountHolderFromDB.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account Holder found with ID:"+ id);
        }else{
            return accountHolderFromDB.get();
        }
    }

    public AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder){
        Optional<AccountHolder> accountHolderFromDB = accountHolderRepository.findById(id);
        if(accountHolderFromDB.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account Holder found with ID:"+ id);
        }else{
            if (accountHolder.getName() != null) {
                accountHolderFromDB.get().setName(accountHolder.getName());
            }
            if (accountHolder.getUsername() != null) {
                accountHolderFromDB.get().setUsername(accountHolder.getUsername());
            }
            accountHolderFromDB.get().setRoles(accountHolder.getRoles());
            if (accountHolder.getDateOfBirth() != null) {
                accountHolderFromDB.get().setDateOfBirth(accountHolder.getDateOfBirth());
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format.");
                }
            if (accountHolder.getAddress() != null){
                accountHolderFromDB.get().setAddress(accountHolder.getAddress());
               }
            if (accountHolder.getMailingAddress() !=null){
                accountHolderFromDB.get().setMailingAddress(accountHolder.getMailingAddress());
            }
        return accountHolderRepository.save(accountHolder);
        }
    }

    public void deleteAccountHolder(Long id){
        Optional<AccountHolder> foundAccountHolder= accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account Holder found with ID:"+ id);
        }else{
        accountHolderRepository.delete(foundAccountHolder.get());
        }
    }

   public List<Account> getAccountList(Long id){
        //get logged user name
       String loggedUsername;
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (principal instanceof UserDetails) {
            loggedUsername = ((UserDetails)principal).getUsername();
       } else {
            loggedUsername = principal.toString();
       }
       Optional<AccountHolder> accountHolderFromDb = accountHolderRepository.findById(id);
       if(accountHolderFromDb.isEmpty()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with ID:"+ id);
       }else{
           if(accountHolderFromDb.get().getUsername() != loggedUsername){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with ID:"+ id);
           }else{
               List<Account>loggedUserAccounts = accountHolderFromDb.get().getAccountList();
               return loggedUserAccounts;
           }
       }
   }

}
