package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.model.ThirdParty;
import com.ironhack.openbank_project.service.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ThirdPartyController {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;


    @PostMapping("/thirdParty")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty saveThirdParty(@RequestBody ThirdParty thirdParty){
        return thirdPartyServiceInterface.addThirdParty(thirdParty);
    }
    @DeleteMapping("/thirdParty/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThirdParty(Long id){
        thirdPartyServiceInterface.deleteThirdParty(id);
    }


}
