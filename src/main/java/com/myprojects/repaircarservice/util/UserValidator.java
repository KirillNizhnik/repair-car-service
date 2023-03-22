package com.myprojects.repaircarservice.util;

import com.myprojects.repaircarservice.jpa.UserJpa;
import com.myprojects.repaircarservice.model.User;
import com.myprojects.repaircarservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class UserValidator implements Validator {


    private  final UserService userService;


    @Autowired
    public UserValidator(UserService userService
                         ) {
        this.userService = userService;

    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    User user = (User)target;
    Optional<User> optionalUser = userService.findByLogin(user.getLogin());
    if (optionalUser.isEmpty()){
        return;
    }
    errors.rejectValue("login", "", "Login is used");

    }
}
