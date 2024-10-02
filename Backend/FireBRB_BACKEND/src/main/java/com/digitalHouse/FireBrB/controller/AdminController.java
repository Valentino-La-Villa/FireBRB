package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.request.AssignAdminRequest;
import com.digitalHouse.FireBrB.auth.AuthenticationService;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private IUserRepository userRepository;

    @PutMapping("/assignAdminPrivileges")
    public ResponseEntity<User> assignAdmin (@RequestBody AssignAdminRequest request) {
        if (userRepository.findByEmail(request.getUserEmail()).isPresent()) {
            return ResponseEntity.ok(authenticationService.assignAdmin(request));
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/revokeAdminPrivileges")
    public ResponseEntity<User> revokeAdmin (@RequestBody AssignAdminRequest request) {
        if (userRepository.findByEmail(request.getUserEmail()).isPresent()) {
            return ResponseEntity.ok(authenticationService.revokeAdmin(request));
        } else return ResponseEntity.notFound().build();
    }
}
