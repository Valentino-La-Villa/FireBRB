package com.digitalHouse.FireBrB.auth;

import com.digitalHouse.FireBrB.enums.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationService authService;

    private static final String ENDPOINT = "/auth";
    private final static String EMAIL = "ferensibiditoilet@gmail.com";
    private final static String PASSWORD = "1234";
    private final static String FIRST_NAME = "Fisi";
    private final static String SURNAME = "Rodriguez";

    @Test
    @Order(1)
    void registerRequest() throws Exception {
        String userRegistered = "{" +
                "\"email\": \""+EMAIL+"\"," +
                "\"password\": \""+PASSWORD+"\"," +
                "\"firstName\": \""+FIRST_NAME+"\"," +
                "\"surname\": \""+SURNAME+"\"" +
                "}";
        mockMvc.perform(post(ENDPOINT + "/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userRegistered)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    @Order(2)
    void loginRequest() throws Exception {
        String userData = "{" +
                "\"email\": \""+EMAIL+"\"," +
                "\"password\": \""+PASSWORD+"\"" +
                "}";

        mockMvc.perform(post(ENDPOINT + "/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userData)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
}