package com.ironhack.openbank_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.openbank_project.DTO.TransferDTO;
import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.Role;
import com.ironhack.openbank_project.repository.AccountHolderRepository;
import com.ironhack.openbank_project.repository.AccountRepository;

import com.ironhack.openbank_project.utils.Address;
import com.ironhack.openbank_project.utils.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Address address = new Address("Americas", 4, "Malaga", "Spain");
        Money money = new Money(new BigDecimal(1000));
        LocalDate dateOfBirth = LocalDate.of(1992, 01, 02);
        List<Role> role = List.of(new Role(null, "ROLE_ACCOUNT_HOLDER"));
        AccountHolder accountHolder1 = new AccountHolder("Mitu", "mitu", "1234", role, dateOfBirth, address, "m@gmail.com");
        AccountHolder accountHolder2 = new AccountHolder("Tsune", "tsune", "1234", role, dateOfBirth, address, "m@gmail.com");
        AccountHolder accountHolder3 = new AccountHolder("Hat", "hat", "1234", role, dateOfBirth, address, "m@gmail.com");

        List.of(
                new Account("1234", money, accountHolder1, null),
                new Account("1234", money, accountHolder2, null),
                new Account("1234", money, accountHolder3, null)
        );

    }
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "mitu",password = "1234",roles = "ROLE_ACCOUNT_HOLDER")
    public void sendTransferAccount_patchStatus_noContent() throws Exception {
        Money amount = new Money(new BigDecimal(100));
        TransferDTO transferDTO= new TransferDTO(amount,2L);
        Address address = new Address("Americas", 4, "Malaga", "Spain");
        Money money = new Money(new BigDecimal(1000));
        LocalDate dateOfBirth = LocalDate.of(1992, 01, 02);
        List<Role> role = List.of(new Role(null, "ROLE_ACCOUNT_HOLDER"));
        AccountHolder accountHolder1 = new AccountHolder("Mitu", "mitu", "1234", role, dateOfBirth, address, "m@gmail.com");
        AccountHolder accountHolder2 = new AccountHolder("Tsune", "tsune", "1234", role, dateOfBirth, address, "m@gmail.com");
        Account account1 = new Account("1234", money, accountHolder1, null);
        Account account2 = new Account("1234", money, accountHolder2, null);

        String body = objectMapper.writeValueAsString(transferDTO);
        mockMvc.perform(patch("/accounts/1/transfer").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(accountRepository.findById(1L).get().getBalance(), account1.getBalance().decreaseAmount(amount));
        assertEquals(accountRepository.findById(transferDTO.getRecipientAccountId()),account2.getBalance().increaseAmount(amount));

    }


}