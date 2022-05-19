package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.ThirdParty;
import com.ironhack.openbank_project.repository.ThirdPartyRepository;
import com.ironhack.openbank_project.service.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    public ThirdParty addThirdParty (ThirdParty thirdParty){
        return thirdPartyRepository.save(thirdParty);
    }

    public void deleteThirdParty (Long id){
        Optional<ThirdParty>thirdPartyFromDb = thirdPartyRepository.findById(id);
        if(thirdPartyFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account Holder found with ID:"+ id);
        }else{
        thirdPartyRepository.delete(thirdPartyFromDb.get());
        }
    }

}
