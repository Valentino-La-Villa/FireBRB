package com.digitalHouse.FireBrB.loader;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        RentableTypeDTO typeAppartment = rentableTypeService.save(new RentableTypeDTO("Appartment"));
        RentableTypeDTO typeCabin = rentableTypeService.save(new RentableTypeDTO("Cabin"));
        RentableTypeDTO typeHotel = rentableTypeService.save(new RentableTypeDTO("Hotel"));
        RentableTypeDTO typeHostel = rentableTypeService.save(new RentableTypeDTO("Hostel"));
        RentableTypeDTO typeHouse = rentableTypeService.save(new RentableTypeDTO("House"));

        // Initializing the only admin
        userRepository.save(User.builder()
                .firstName("Valentino")
                .surname("La Villa")
                .email("duamnlavilla@gmail.com")
                .password(passwordEncoder.encode("adminadmin"))
                .role(Role.ADMIN)
                .build());

        rentableService.save(new RentableDTO(
                "Gurruchaga 125",
                "S2005",
                12.41,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ))
        ));
    }
}
