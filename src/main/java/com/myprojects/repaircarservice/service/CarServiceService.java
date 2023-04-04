package com.myprojects.repaircarservice.service;

import com.myprojects.repaircarservice.jpa.CarServiceJpa;
import com.myprojects.repaircarservice.model.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarServiceService implements Register<CarService> {

    private static final String ROLE_SERVICE = "ROLE_SERVICE";

    private final CarServiceJpa carServiceJpa;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CarServiceService(CarServiceJpa carServiceJpa, PasswordEncoder passwordEncoder) {
        this.carServiceJpa = carServiceJpa;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<CarService> findByLogin(String login){
        return carServiceJpa.findByLogin(login);
    }

    @Transactional
    public void register(CarService carService){
        String encodedPassword = passwordEncoder.encode(carService.getPassword());
        carService.setPassword(encodedPassword);
        carService.setRole(ROLE_SERVICE);
        carServiceJpa.save(carService);
    }

    @Transactional
    public void update(CarService sessionCarService, CarService updatedCarService) {

        CarService foundCarService = carServiceJpa.findByLogin(sessionCarService.getLogin()).get();
        foundCarService.setName(updatedCarService.getName());
        foundCarService.setPhoneNumbers(updatedCarService.getPhoneNumbers());
        foundCarService.setEmail(updatedCarService.getEmail());
    }
}
