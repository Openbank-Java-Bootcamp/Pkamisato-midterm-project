package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.service.interfaces.CheckingServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckingController {

    @Autowired
    CheckingServiceInterface checkingServiceInterface;

    @PostMapping("/checkingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking saveCheckingAccount(@RequestBody @Valid Checking checking){
        return checkingServiceInterface.addChecking(checking);
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



}
