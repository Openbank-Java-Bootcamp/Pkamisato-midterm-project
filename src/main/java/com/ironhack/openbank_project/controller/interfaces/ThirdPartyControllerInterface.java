package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.ThirdParty;

public interface ThirdPartyControllerInterface {

    ThirdParty saveThirdParty(ThirdParty thirdParty);

    void deleteThirdParty(Long id);
}
