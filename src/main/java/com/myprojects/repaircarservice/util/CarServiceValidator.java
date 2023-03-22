package com.myprojects.repaircarservice.util;

import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.service.CarServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CarServiceValidator implements Validator {


    private final CarServiceService carServiceService;

    @Autowired
    public CarServiceValidator(CarServiceService carServiceService) {
        this.carServiceService = carServiceService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CarService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarService carService = (CarService) target;
        Optional<CarService> optionalUser = carServiceService.findByLogin(carService.getLogin());
        if (optionalUser.isEmpty()){
            return;
        }
        errors.rejectValue("login", "", "Login Service is used");
    }
}
