package com.myprojects.repaircarservice.service;

import com.myprojects.repaircarservice.jpa.CarServiceJpa;
import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.security.CarServiceDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceDetailsService implements UserDetailsService {
    private final CarServiceJpa carServiceJpa;

    public CarServiceDetailsService(CarServiceJpa carService) {
        this.carServiceJpa = carService;
    }

    @Override
    public UserDetails loadUserByUsername(String carServiceLogin) throws UsernameNotFoundException {
        Optional<CarService> optionalCarService = carServiceJpa.findByLogin(carServiceLogin);
        if(optionalCarService.isEmpty())
            throw new UsernameNotFoundException("Service not found!");
        return new CarServiceDetails(optionalCarService.get());
    }
}
