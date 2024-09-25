package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.auth.AuthenticationService;
import com.digitalHouse.FireBrB.auth.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationService authService;

    private final static String ENDPOINT = "/admin";
    private final static String EMAIL = "ferensibiditoilet@gmail.com";
    private final static String PASSWORD = "1234";
    private final static String FIRST_NAME = "Fisi";
    private final static String SURNAME = "Rodriguez";

    public void loadData() {
        authService.register(new RegisterRequest(FIRST_NAME, SURNAME, EMAIL, PASSWORD));
    }

    @Test
    void assignAdminRequest() throws Exception {
        loadData();
        String userData = "{\"userEmail\": \""+EMAIL+"\"}";

        mockMvc.perform(put(ENDPOINT + "/assignAdminPrivileges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userData)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.surname").value(SURNAME))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void revokeAdminRequest() throws Exception {
        loadData();
        String userData = "{\"userEmail\": \""+EMAIL+"\"}";

        mockMvc.perform(put(ENDPOINT + "/revokeAdminPrivileges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userData)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.surname").value(SURNAME))
                .andExpect(jsonPath("$.role").value("USERREGULAR"));
    }

}