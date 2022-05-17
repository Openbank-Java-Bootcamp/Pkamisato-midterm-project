package com.ironhack.openbank_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.openbank_project.model.User;
import com.ironhack.openbank_project.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository userRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<User> users = userRepository.saveAll(
                List.of(
                        new User(null, "John Doe", "john", "1234", new ArrayList<>()),
                        new User(null, "James Smith", "james", "1234", new ArrayList<>()),
                        new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()),
                        new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>())
                )
        );
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getUsers_AllSuccessful() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("John Doe"));
        assertTrue(result.getResponse().getContentAsString().contains("James Smith"));
        assertTrue(result.getResponse().getContentAsString().contains("Jane Carry"));
        assertTrue(result.getResponse().getContentAsString().contains("Chris Anderson"));
    }
















}