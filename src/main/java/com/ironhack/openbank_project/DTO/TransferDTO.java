package com.ironhack.openbank_project.DTO;

import com.ironhack.openbank_project.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {

    private Money transferAmount;
    private Long recipientAccountId;
}
