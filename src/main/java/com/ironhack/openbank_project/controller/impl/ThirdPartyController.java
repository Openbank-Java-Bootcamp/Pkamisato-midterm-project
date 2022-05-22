package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.DTO.ThirdPartyDTO;
import com.ironhack.openbank_project.DTO.TransferThirdPartyDTO;
import com.ironhack.openbank_project.controller.interfaces.ThirdPartyControllerInterface;
import com.ironhack.openbank_project.model.ThirdParty;
import com.ironhack.openbank_project.service.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;


    @PostMapping("/thirdParties")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty saveThirdParty(@RequestBody ThirdPartyDTO thirdPartyDTO){
        ThirdParty thirdParty = new ThirdParty(thirdPartyDTO.getName(), thirdPartyDTO.getMessage());
        return thirdPartyServiceInterface.addThirdParty(thirdParty);
    }
    @DeleteMapping("/thirdParties/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThirdParty(@PathVariable(name = "id") Long id){
        thirdPartyServiceInterface.deleteThirdParty(id);
    }

    @PatchMapping("/thirdParties/{hashedKey}/send_transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendTransfer(@PathVariable(name = "hashedKey") byte[] hashedKey, @RequestBody TransferThirdPartyDTO transferThirdPartyDTO){
        thirdPartyServiceInterface.sendTransferToAccount(hashedKey,transferThirdPartyDTO);
    }

    @PatchMapping("/thirdParties/{hashedKey}/receive_transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveTransfer(@PathVariable(name = "hashedKey") byte[] hashedKey, @RequestBody TransferThirdPartyDTO transferThirdPartyDTO){
        thirdPartyServiceInterface.receiveTransferFromAccount(hashedKey,transferThirdPartyDTO);
    }

}
