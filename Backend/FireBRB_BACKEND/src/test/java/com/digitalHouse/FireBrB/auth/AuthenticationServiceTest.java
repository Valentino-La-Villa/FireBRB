package com.digitalHouse.FireBrB.auth;

import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String EMAIL = "ferensibiditoilet@gmail.com";
    private final static String PASSWORD = "1234";
    private final static String FIRST_NAME = "Fisi";
    private final static String SURNAME = "Rodriguez";

    @BeforeEach
    public void loadData() {
        authenticationService.register(new RegisterRequest(FIRST_NAME, SURNAME, EMAIL, PASSWORD));
    }

    @Test
    void register() {
        Optional<User> optionalUser = userRepository.findByEmail(EMAIL);
        User user = optionalUser.get();
        assertAll(
                () -> assertEquals(user.getEmail(), EMAIL),
                () -> assertEquals(user.getFirstName(), FIRST_NAME),
                () -> assertEquals(user.getSurname(), SURNAME),
                () -> assertEquals(user.getRole(), Role.USERREGULAR)
        );
    }

    @Test
    void login() {
        AuthenticationResponse response = authenticationService.login(new AuthenticationRequest(EMAIL, PASSWORD));
        assertTrue(response.getToken().length() > 20);
    }

    @Test
    void assignAdmin() {
        User user = authenticationService.assignAdmin(new AssignAdminRequest(EMAIL));
        assertEquals(user.getRole(), Role.ADMIN);
    }

    @Test
    void revokeAdmin() {
        authenticationService.assignAdmin(new AssignAdminRequest(EMAIL));
        User user = authenticationService.revokeAdmin(new AssignAdminRequest(EMAIL));
        assertEquals(user.getRole(), Role.USERREGULAR);
    }
}