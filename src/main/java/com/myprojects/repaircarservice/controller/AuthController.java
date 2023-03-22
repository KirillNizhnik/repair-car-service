package com.myprojects.repaircarservice.controller;

import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.model.User;
import com.myprojects.repaircarservice.service.CarServiceService;
import com.myprojects.repaircarservice.service.UserService;
import com.myprojects.repaircarservice.util.CarServiceValidator;
import com.myprojects.repaircarservice.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {


    private final UserValidator userValidator;
    private final UserService userService;

    private final CarServiceValidator carServiceValidator;

    private final CarServiceService carServiceService;

    @Autowired
    public AuthController(UserValidator userValidator, UserService userService, CarServiceValidator carServiceValidator, CarServiceService carServiceService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.carServiceValidator = carServiceValidator;
        this.carServiceService = carServiceService;
    }

    /**
     * User Auth Controller Methods
     * */
    @GetMapping("/user/login-user")
    public String loginPage() {
        return "auth/user/login-user";
    }

    @GetMapping("/user/registration-user")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "/auth/user/registration-user";
    }

    @PostMapping("/user/registration-user")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/user/registration-user";
        }
        userService.register(user);

        return "redirect:/user/login-user";
    }



    /*
    * Service Auth Controller Methods
     */
    @GetMapping("/service/login-service")
    public String loginPageService() {
        return "auth/service/login-service";
    }

    @GetMapping("/service/registration-service")
    public String registrationPageService(@ModelAttribute("service") CarService carService) {
        return "auth/service/registration-service";
    }

    @PostMapping("/service/registration-service")
    public String performRegistrationService(@ModelAttribute("service") @Valid CarService carService,
                                             BindingResult bindingResult) {
        carServiceValidator.validate(carService, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/service/registration-service";
        }
        carServiceService.register(carService);

        return "redirect:/service/login-service";
    }
}
