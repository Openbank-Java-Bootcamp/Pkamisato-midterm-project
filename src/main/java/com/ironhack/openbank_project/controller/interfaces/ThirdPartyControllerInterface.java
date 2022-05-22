package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.DTO.ThirdPartyDTO;
import com.ironhack.openbank_project.DTO.TransferThirdPartyDTO;
import com.ironhack.openbank_project.model.ThirdParty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ThirdPartyControllerInterface {

    ThirdParty saveThirdParty(ThirdPartyDTO thirdPartyDTO);

    void deleteThirdParty(Long id);

    void sendTransfer(@PathVariable(name = "hashedKey") byte[] hashedKey, @RequestBody TransferThirdPartyDTO transferThirdPartyDTO);

    void receiveTransfer(@PathVariable(name = "hashedKey") byte[] hashedKey, @RequestBody TransferThirdPartyDTO transferThirdPartyDTO);
}
