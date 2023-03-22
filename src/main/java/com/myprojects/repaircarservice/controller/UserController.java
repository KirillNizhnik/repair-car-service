package com.myprojects.repaircarservice.controller;

import com.myprojects.repaircarservice.model.Orders;
import com.myprojects.repaircarservice.model.User;
import com.myprojects.repaircarservice.security.UserDetailsImpl;
import com.myprojects.repaircarservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/user-menu")

public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String getUsers(Model model){
        User user = getSessionUser();
        List<Orders> ordersList = userService.getOrders(user.getId());
        model.addAttribute("orders", ordersList);
        return "user/user-menu";
    }


    private User getSessionUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        return userDetails.user();


    }
}
