package com.digitalHouse.FireBrB.loader;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialData implements CommandLineRunner {
    private final IRentableService rentableService;
    private final IUserRepository userRepository;
    private final IRentableTypeService rentableTypeService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Initializing rentable types
        rentableTypeService.save(new RentableTypeDTO("Appartment"));
        rentableTypeService.save(new RentableTypeDTO("Cabin"));
        rentableTypeService.save(new RentableTypeDTO("Hotel"));
        rentableTypeService.save(new RentableTypeDTO("Hostel"));

        // Initializing the only admin
        userRepository.save(User.builder()
                .firstName("Valentino")
                .surname("La Villa")
                .email("duamnlavilla@gmail.com")
                .password(passwordEncoder.encode("adminadmin"))
                .role(Role.ADMIN)
                .build());
    }
}
