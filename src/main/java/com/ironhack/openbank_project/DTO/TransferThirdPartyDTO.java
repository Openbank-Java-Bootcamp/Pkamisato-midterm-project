package com.ironhack.openbank_project.DTO;

import com.ironhack.openbank_project.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferThirdPartyDTO {

    private Money amount;
    private Long accountId;
    private String secretKey;
}
