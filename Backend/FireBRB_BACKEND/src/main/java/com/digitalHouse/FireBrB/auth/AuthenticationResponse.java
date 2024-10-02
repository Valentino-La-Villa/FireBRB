package com.digitalHouse.FireBrB.auth;


import com.digitalHouse.FireBrB.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Role role;
    private String firstName;
    private String surname;
    private String email;
}
