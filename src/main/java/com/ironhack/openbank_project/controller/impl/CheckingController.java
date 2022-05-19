package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.DTO.NewCheckingDTO;
import com.ironhack.openbank_project.controller.interfaces.CheckingControllerInterface;
import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.service.interfaces.CheckingServiceInterface;
import com.ironhack.openbank_project.utils.Money;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CheckingController implements CheckingControllerInterface {

    @Autowired
    CheckingServiceInterface checkingServiceInterface;

    @PostMapping("/checkingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveCheckingAccount(@RequestBody @Valid NewCheckingDTO newCheckingDTO){
        return checkingServiceInterface.addChecking(newCheckingDTO);
    }

    @GetMapping("/checkingAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountById(@PathVariable (name = "id") Long id){
        return checkingServiceInterface.getCheckingById(id);
    }

    @PutMapping("/checkingAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Checking updateCheckingAccount(@PathVariable (name = "id") Long id, @RequestBody Checking checking){
        return  checkingServiceInterface.updateChecking(id,checking);
    }

    @DeleteMapping("/checkingAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCheckingAccount(@PathVariable (name = "id") Long id) {
        checkingServiceInterface.deleteChecking(id);
    }

    @GetMapping("/checkingAccounts/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getActualBalance(@PathVariable (name = "id") Long id){
        return checkingServiceInterface.getActualBalance(id);
    }



}
