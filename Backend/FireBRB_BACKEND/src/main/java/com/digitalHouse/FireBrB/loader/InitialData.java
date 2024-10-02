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
        RentableTypeDTO typeHotel = rentableTypeService.save(new RentableTypeDTO("Hotel", "https://images.unsplash.com/photo-1455587734955-081b22074882?q=80&w=1920&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        RentableTypeDTO typeCabin = rentableTypeService.save(new RentableTypeDTO("Cabin", "https://images.unsplash.com/photo-1449158743715-0a90ebb6d2d8?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        RentableTypeDTO typeAppartment = rentableTypeService.save(new RentableTypeDTO("Appartment", "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?q=80&w=1980&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        RentableTypeDTO typeHostel = rentableTypeService.save(new RentableTypeDTO("Hostel", "https://images.unsplash.com/photo-1709805619372-40de3f158e83?q=80&w=2095&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        RentableTypeDTO typeHouse = rentableTypeService.save(new RentableTypeDTO("House", "https://images.unsplash.com/photo-1504615755583-2916b52192a3?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));

        // Initializing the only admin
        userRepository.save(User.builder()
                .firstName("Valentino")
                .surname("La Villa")
                .email("valentinolavilla@developer.com")
                .password(passwordEncoder.encode("adminadmin"))
                .role(Role.ADMIN)
                .build());

        rentableService.save(new RentableDTO(
                "Complejo Rodriguez",
                "Gurruchaga 125",
                "Rosario",
                "Santa Fe",
                "Argentina",
                12.41,
                4.3,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1519710164239-da123dc03ef4?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505015920881-0f83c2f7c95e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505819244306-ef53954f9648?q=80&w=1899&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        )),
                "https://maps.app.goo.gl/Nn2C2DY37f5mqwCx5"
        ));

        rentableService.save(new RentableDTO(
                "Hotel del Sel",
                "Tucumán 1949",
                "Virreyes",
                "Ciudad Autónoma de Buenos Aires",
                "Argentina",
                21.50,
                4.9,
                typeHotel.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1444676632488-26a136c45b9b?q=80&w=1937&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1694560446249-240043f487bc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1693684209765-75863b0a26f8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/jWW69fdigv2NSwZ76"
        ));
        rentableService.save(new RentableDTO(
                "Cabañas San Miguel",
                "Avenida Los Quebrachos",
                "San Marcos Sierras",
                "Córdoba",
                "Argentina",
                8.50,
                3.0,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1531183436556-51f742660c8d?q=80&w=1880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1563373983-04e07c359192?q=80&w=2088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1487695652027-48e475bfa86f?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1551927411-95e412943b58?q=80&w=1949&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1430000589629-f04107b5597c?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/24KbcdF4QAdGNtbq8"
        ));
        rentableService.save(new RentableDTO(
                "Muelle de los Sueños",
                "Moreno 1022",
                "Mar del Plata",
                "Buenos Aires",
                "Argentina",
                30.21,
                3.6,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1571598301081-36702bef5e81?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1527772482340-7895c3f2b3f7?q=80&w=1902&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1683769251695-963095b23d67?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531835551805-16d864c8d311?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1529408632839-a54952c491e5?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/HshWbUN5vuEwkm4a8"
        ));
        rentableService.save(new RentableDTO(
                "Puerto del Fin del Mundo",
                "Isla Gran Malvina 898",
                "Ushuaia",
                "Tierra del Fuego",
                "Argentina",
                17.31,
                5.0,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1587753969545-6f3507920c9d?q=80&w=2026&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1696483770666-e50606d7267c?q=80&w=1933&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1587181497477-735b0504cb66?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1674478272062-f68224f203ad?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGllcnJhJTIwZGVsJTIwZnVlZ298ZW58MHx8MHx8fDA%3D",
                        "https://images.unsplash.com/photo-1709037448253-497296f50d12?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHRpZXJyYSUyMGRlbCUyMGZ1ZWdvfGVufDB8fDB8fHww"
                )),
                "https://maps.app.goo.gl/uCLPVkWsBVWxBowF9"
        ));
        rentableService.save(new RentableDTO(
                "Barrio Chino Hostel",
                "Montañeses 2439",
                "Belgrano",
                "Buenos Aires",
                "Argentina",
                6.31,
                3.2,
                typeHostel.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1506639785221-c7b628b924ed?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1526186550435-406043dc9904?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1508976780522-94b939cc46e0?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1717614958411-e39eda3dd801?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1529445654487-3bde9b55e0b2?q=80&w=1989&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/2NSMZtF74Wc59zqr5"
        ));


        rentableService.save(new RentableDTO(
                "Paraíso Playero",
                "Av. 41 238",
                "Santa Teresita",
                "Buenos Aires",
                "Argentina",
                4.26,
                2.5,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1566254900834-725c05a97f89?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1684175656035-b0b62f72b5a2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1677691961682-490fc5c593bf?q=80&w=1895&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531514381259-8c9fedc910b8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1521170813716-0b3f42fcfb65?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/ikhT4y6qsKrZMfos9"
        ));

                rentableService.save(new RentableDTO(
                "Residio de la Paz",
                "Cacique Linares 961",
                "El Bolsón",
                "Río Negro",
                "Argentina",
                37.33,
                5.0,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1588557132645-ff567110cafd?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1699398227790-22147bd36a41?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1697730274475-d5ae903038fe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1675439070021-70d1e8b0bec1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1571864376668-0b5e413eafe5?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/RN5WiU1XegRgbEUL6"
        ));

        rentableService.save(new RentableDTO(
                "Refugio de los Montañeses",
                "Lautaro 773",
                "Palena",
                "Los Lagos",
                "Chile",
                17.10,
                3.7,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1691215862710-f989aa959841?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1683587501679-c60f0c750336?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1667759327541-d541b044b71f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1664123212369-87640d62f17e?q=80&w=2084&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1667759045595-406df3c0dd55?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/GonWv1G78LqT3Fhn9"
        ));

        rentableService.save(new RentableDTO(
                "Complejo La Florida",
                "Guayaquil 330",
                "Rosario",
                "Santa Fe",
                "Argentina",
                18.23,
                4.0,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1519710164239-da123dc03ef4?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505015920881-0f83c2f7c95e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505819244306-ef53954f9648?q=80&w=1899&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/PZ6wgk3rgbwDDyye7"
        ));


        rentableService.save(new RentableDTO(
                "Corazón de la Urbe", // Buenos Aires
                "Rojas 43",
                "Caballito",
                "Ciudad Autónoma de Buenos Aires",
                "Argentina",
                12.50,
                1.8,
                typeHotel.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1694560446249-240043f487bc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1444676632488-26a136c45b9b?q=80&w=1937&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1693684209765-75863b0a26f8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/J9HLBtnTHQvMTTo38"
        ));
        rentableService.save(new RentableDTO(
                "Hytter i Ensomhet", // Cabin in the woods
                "Myrsletta 25",
                "Jessheim",
                "Akershus",
                "Norway",
                45.12,
                        3.8,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1563373983-04e07c359192?q=80&w=2088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531183436556-51f742660c8d?q=80&w=1880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1487695652027-48e475bfa86f?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1551927411-95e412943b58?q=80&w=1949&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1430000589629-f04107b5597c?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/UmppigNS6pVGYJZ39"
        ));
        rentableService.save(new RentableDTO(
                "Heart of Kessler Resort", // Metropolis
                "849 W Colorado Blvd",
                "Dallas",
                "Texas",
                "USA",
                        10.00,
                3.71,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1527772482340-7895c3f2b3f7?q=80&w=1902&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1571598301081-36702bef5e81?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1683769251695-963095b23d67?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531835551805-16d864c8d311?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1529408632839-a54952c491e5?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/RZQN19Sa3UZ6BCZf8"
        ));
        rentableService.save(new RentableDTO(
                "The Pearl of Edmonton", // Patagonia TDF
                "82 Ave NW",
                "Edmonton",
                "Alberta",
                "Canada",
                8.41,
                2.8,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1696483770666-e50606d7267c?q=80&w=1933&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1587753969545-6f3507920c9d?q=80&w=2026&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1587181497477-735b0504cb66?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1674478272062-f68224f203ad?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGllcnJhJTIwZGVsJTIwZnVlZ298ZW58MHx8MHx8fDA%3D",
                        "https://images.unsplash.com/photo-1709037448253-497296f50d12?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHRpZXJyYSUyMGRlbCUyMGZ1ZWdvfGVufDB8fDB8fHww"
                )),
                "https://maps.app.goo.gl/ttoKZ8hiyMDzjdcf9"
        ));
        rentableService.save(new RentableDTO(
                "Frisco's Epicenter of Chinatown", // Chinatown
                "Clay St. 1030",
                "San Francisco",
                "California",
                "USA",
                21.33,
                4.1,
                typeHostel.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1526186550435-406043dc9904?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1506639785221-c7b628b924ed?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1508976780522-94b939cc46e0?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1717614958411-e39eda3dd801?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1529445654487-3bde9b55e0b2?q=80&w=1989&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/wdvQSRTDYVFHTSaZ7"
        ));


        rentableService.save(new RentableDTO(
                "Παραθαλάσσιο ξενοδοχείο", // Beach
                "Tzanimpei Grigoraki",
                "Githio",
                "Peloponnese",
                "Greece",
                9.12,
                1.3,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://plus.unsplash.com/premium_photo-1684175656035-b0b62f72b5a2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1566254900834-725c05a97f89?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1677691961682-490fc5c593bf?q=80&w=1895&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531514381259-8c9fedc910b8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1521170813716-0b3f42fcfb65?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/YSyt6GJne3yrMdWh7"
        ));

        rentableService.save(new RentableDTO(
                "Ferensi mökit", // Patagonia
                "Tornedalsvägen 45",
                "Pajala",
                "Norbotten",
                "Finland",
                7.41,
                2.2,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1699398227790-22147bd36a41?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1588557132645-ff567110cafd?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1697730274475-d5ae903038fe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1675439070021-70d1e8b0bec1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1571864376668-0b5e413eafe5?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/G73kN4QiEuSGi6B16"
        ));

        rentableService.save(new RentableDTO(
                "Soul of the Forest Cabins", // Patagonia
                "9273 Voyageur Hwy",
                "Margie",
                "Minnessota",
                "USA",
                6.21,
                3.3,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1683587501679-c60f0c750336?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1691215862710-f989aa959841?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1667759327541-d541b044b71f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1664123212369-87640d62f17e?q=80&w=2084&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1667759045595-406df3c0dd55?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/8hSqGK5D6G9GgYy7A"
        ));


        rentableService.save(new RentableDTO(
                "Johnston's Family Cottage", // La florida
                "Mandin St. 2",
                "Alexandra Headland",
                "Queensland",
                "Argentina",
                7.12,
                2.8,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1519710164239-da123dc03ef4?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505015920881-0f83c2f7c95e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1505819244306-ef53954f9648?q=80&w=1899&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/R9arRps3TiR5BDgWA"
        ));

        rentableService.save(new RentableDTO(
                "La Perla  del AMBA", // Buenos Aires
                "San Nicolás 357",
                "Floresta",
                "Ciudad Autónoma de Buenos Aires",
                "Argentina",
                5.41,
                1.2,
                typeHotel.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1444676632488-26a136c45b9b?q=80&w=1937&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1694560446249-240043f487bc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1693684209765-75863b0a26f8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/joNyP2yvYAqT83356"
        ));
        rentableService.save(new RentableDTO(
                "Whispering Oaks Resort", // Cabin in the woods
                "Stevens Pass Hwy63501 ",
                "Gold Bar",
                "Washington",
                "USA",
                10.00,
                        3.4,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1487695652027-48e475bfa86f?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531183436556-51f742660c8d?q=80&w=1880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1563373983-04e07c359192?q=80&w=2088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1551927411-95e412943b58?q=80&w=1949&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1430000589629-f04107b5597c?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/T6NKpFdbncTSmATe9"
        ));
        rentableService.save(new RentableDTO(
                "The Loyal Servant", // Metropolis
                "Cox St 25",
                "Birmingham",
                "Birmingham",
                "England",
                        13.44,
                4.2,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1529408632839-a54952c491e5?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1571598301081-36702bef5e81?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1527772482340-7895c3f2b3f7?q=80&w=1902&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1683769251695-963095b23d67?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531835551805-16d864c8d311?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/vBTxQh59ZdwBCDg77"
        ));
        rentableService.save(new RentableDTO(
                "Estancia Chaltén Querido", // Patagonia TDF
                "Halvorsen 98",
                "El Chaltén",
                "Santa Cruz",
                "Argentina",
                14.41,
                4.9,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1587181497477-735b0504cb66?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1587753969545-6f3507920c9d?q=80&w=2026&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1696483770666-e50606d7267c?q=80&w=1933&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1674478272062-f68224f203ad?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGllcnJhJTIwZGVsJTIwZnVlZ298ZW58MHx8MHx8fDA%3D",
                        "https://images.unsplash.com/photo-1709037448253-497296f50d12?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHRpZXJyYSUyMGRlbCUyMGZ1ZWdvfGVufDB8fDB8fHww"
                )),
                "https://maps.app.goo.gl/dSNhicYaEVNrfiWx9"
        ));
        rentableService.save(new RentableDTO(
                "Piccolo Resort Cinese", // Chinatown
                "Via Paolo Lomazzo 17",
                "Milan",
                "Lombardy",
                "Italy",
                6.31,
                3.7,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1508976780522-94b939cc46e0?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1506639785221-c7b628b924ed?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1526186550435-406043dc9904?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1717614958411-e39eda3dd801?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1529445654487-3bde9b55e0b2?q=80&w=1989&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )),
                "https://maps.app.goo.gl/LwDZUhyHZGRGzN479"
        ));


        rentableService.save(new RentableDTO(
                "Miami Beach", // Beach
                "Old Cutler Rd 18198",
                "Miami",
                "Florida",
                "USA",
                13.41,
                3.0,
                typeAppartment.getId(),
                new ArrayList<>(List.of(
                        "https://plus.unsplash.com/premium_photo-1677691961682-490fc5c593bf?q=80&w=1895&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1566254900834-725c05a97f89?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1684175656035-b0b62f72b5a2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1531514381259-8c9fedc910b8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1521170813716-0b3f42fcfb65?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/ZC6N6AHvzksm2gW86"
        ));

        rentableService.save(new RentableDTO(
                "Paso de los Pingüinos", // Patagonia
                "RN3 2953",
                "Tolhuin",
                "Tierra del Fuego",
                "Argentina",
                9.41,
                3.1,
                typeHouse.getId(),
                new ArrayList<>(List.of(
                        "https://plus.unsplash.com/premium_photo-1697730274475-d5ae903038fe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1588557132645-ff567110cafd?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1699398227790-22147bd36a41?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://plus.unsplash.com/premium_photo-1675439070021-70d1e8b0bec1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1571864376668-0b5e413eafe5?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/Lwacw2SD2JbwFSE2A"
        ));

        rentableService.save(new RentableDTO(
                "Lago de Cristal Resort", // Patagonia
                "RN3 3075",
                "Lapataia",
                "Tierra del Fuego",
                "Argentina",
                10.50,
                4.7,
                typeCabin.getId(),
                new ArrayList<>(List.of(
                        "https://images.unsplash.com/photo-1667759327541-d541b044b71f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1691215862710-f989aa959841?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1683587501679-c60f0c750336?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1664123212369-87640d62f17e?q=80&w=2084&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        "https://images.unsplash.com/photo-1667759045595-406df3c0dd55?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
                "https://maps.app.goo.gl/ggWgLSL38Wvo1VZx5"
        ));













//
//
//        rentableService.save(new RentableDTO(
//                "Complejo Rodriguez", // La florida
//                "Gurruchaga 125",
//                "Rosario",
//                "Santa Fe",
//                "Argentina",
//                12.41,
//                4.3,
//                typeHouse.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1519710164239-da123dc03ef4?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1505015920881-0f83c2f7c95e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1505819244306-ef53954f9648?q=80&w=1899&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                )),
//                "https://maps.app.goo.gl/Nn2C2DY37f5mqwCx5"
//        ));
//
//        rentableService.save(new RentableDTO(
//                "", // Buenos Aires
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                2.5,
//                typeHotel.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1444676632488-26a136c45b9b?q=80&w=1937&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1694560446249-240043f487bc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1693684209765-75863b0a26f8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                )),
//                ""
//        ));
//        rentableService.save(new RentableDTO(
//                "", // Cabin in the woods
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                        2.5,
//                typeCabin.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1531183436556-51f742660c8d?q=80&w=1880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1563373983-04e07c359192?q=80&w=2088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1487695652027-48e475bfa86f?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1551927411-95e412943b58?q=80&w=1949&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1430000589629-f04107b5597c?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                )),
//                ""
//        ));
//        rentableService.save(new RentableDTO(
//                "", // Metropolis
//                "",
//                "",
//                "",
//                "",
//                        10.00,
//                2.5,
//                typeAppartment.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1571598301081-36702bef5e81?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1527772482340-7895c3f2b3f7?q=80&w=1902&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://plus.unsplash.com/premium_photo-1683769251695-963095b23d67?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1531835551805-16d864c8d311?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1529408632839-a54952c491e5?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                )),
//                ""
//        ));
//        rentableService.save(new RentableDTO(
//                "", // Patagonia TDF
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                2.5,
//                typeHouse.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1587753969545-6f3507920c9d?q=80&w=2026&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1696483770666-e50606d7267c?q=80&w=1933&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1587181497477-735b0504cb66?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1674478272062-f68224f203ad?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dGllcnJhJTIwZGVsJTIwZnVlZ298ZW58MHx8MHx8fDA%3D",
//                        "https://images.unsplash.com/photo-1709037448253-497296f50d12?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHRpZXJyYSUyMGRlbCUyMGZ1ZWdvfGVufDB8fDB8fHww"
//                )),
//                ""
//        ));
//        rentableService.save(new RentableDTO(
//                "", // Chinatown
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                2.5,
//                typeHostel.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1506639785221-c7b628b924ed?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1526186550435-406043dc9904?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1508976780522-94b939cc46e0?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1717614958411-e39eda3dd801?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1529445654487-3bde9b55e0b2?q=80&w=1989&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                )),
//                ""
//        ));
//
//
//        rentableService.save(new RentableDTO(
//                "", // Beach
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                2.5,
//                typeAppartment.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1566254900834-725c05a97f89?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://plus.unsplash.com/premium_photo-1684175656035-b0b62f72b5a2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://plus.unsplash.com/premium_photo-1677691961682-490fc5c593bf?q=80&w=1895&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1531514381259-8c9fedc910b8?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1521170813716-0b3f42fcfb65?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
//                ""
//        ));
//
//        rentableService.save(new RentableDTO(
//                "", // Patagonia
//                "",
//                "",
//                "",
//                "",
//                10.00,
//                2.5,
//                typeHouse.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1588557132645-ff567110cafd?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1699398227790-22147bd36a41?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://plus.unsplash.com/premium_photo-1697730274475-d5ae903038fe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://plus.unsplash.com/premium_photo-1675439070021-70d1e8b0bec1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1571864376668-0b5e413eafe5?q=80&w=1888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
//                ""
//        ));
//
//        rentableService.save(new RentableDTO(
//                "", // Patagonia
//                "",
//                "",
//                " ",
//                "",
//                10.00,
//                2.5,
//                typeCabin.getId(),
//                new ArrayList<>(List.of(
//                        "https://images.unsplash.com/photo-1691215862710-f989aa959841?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1683587501679-c60f0c750336?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1667759327541-d541b044b71f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1664123212369-87640d62f17e?q=80&w=2084&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                        "https://images.unsplash.com/photo-1667759045595-406df3c0dd55?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")),
//                ""
//        ));
    }
}
