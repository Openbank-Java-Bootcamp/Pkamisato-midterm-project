package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.ThirdParty;

public interface ThirdPartyServiceInterface {

    ThirdParty addThirdParty(ThirdParty thirdParty);

    void deleteThirdParty(Long id);
}