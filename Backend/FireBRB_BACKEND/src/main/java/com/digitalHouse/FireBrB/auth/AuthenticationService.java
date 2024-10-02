package com.digitalHouse.FireBrB.auth;

import com.digitalHouse.FireBrB.configuration.JwtService;
import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.request.AssignAdminRequest;
import com.digitalHouse.FireBrB.request.AuthenticationRequest;
import com.digitalHouse.FireBrB.request.RefreshTokenRequest;
import com.digitalHouse.FireBrB.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USERREGULAR)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .build();
    }

    public User assignAdmin(AssignAdminRequest request) {
        var user = userRepository.findByEmail(request.getUserEmail())
                .orElseThrow();
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    public User revokeAdmin(AssignAdminRequest request) {
        var user = userRepository.findByEmail(request.getUserEmail())
                .orElseThrow();
        user.setRole(Role.USERREGULAR);
        return userRepository.save(user);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getUserEmail());
        if (jwtService.isTokenExpired(request.getToken()) && userOptional.isPresent()) {
            User user = userOptional.get();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .role(user.getRole())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .surname(user.getSurname())
                    .build();
        }
        else return new AuthenticationResponse();
    }
}
