package com.myprojects.repaircarservice.controller;


import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.model.Orders;
import com.myprojects.repaircarservice.model.User;
import com.myprojects.repaircarservice.security.CarServiceDetails;
import com.myprojects.repaircarservice.service.CarServiceService;
import com.myprojects.repaircarservice.service.OrdersService;
import com.myprojects.repaircarservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/service/service-menu")
public class ServiceController {

    private final UserService userService;
    private final OrdersService ordersService;

    private final CarServiceService carServiceService;


    @Autowired
    public ServiceController(UserService userService, OrdersService ordersService, CarServiceService carServiceService) {
        this.userService = userService;
        this.ordersService = ordersService;
        this.carServiceService = carServiceService;
    }

    @GetMapping("")
    public String getMenuPage(Model model) {
        CarService carService = getSessionCarService();
        model.addAttribute("carService", carService);
    return "/service/service-menu";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "service/users";
    }

    @GetMapping("/users/search")
    public String getSearchUsersPage(@RequestParam("surname") String surname,
                              Model model) {
        List<User> users = userService.findUsersBySurname(surname);
        model.addAttribute("users", users);
        return "service/users-search";
    }

    @GetMapping("/activity-orders")
    public String getActivityOrdersPage(Model model,
                                    HttpSession httpSession){
       CarService carService = getSessionCarService();


        List<Orders> orders = ordersService.findActivityOrders(carService);


        model.addAttribute("orders", orders);
        return "service/activity-orders";
    }

    @GetMapping("/archive-orders")
    public String getArchiveOrdersPage(Model model,
                                    HttpSession httpSession){
        CarService carService = getSessionCarService();


        List<Orders> orders = ordersService.findArchiveOrders(carService);


        model.addAttribute("orders", orders);
        return "service/archive-orders";
    }






    @GetMapping("/create-orders")
    public String getOrdersPage(
                                   HttpSession httpSession,
                                   Model model){

        CarService carService = getSessionCarService();
        List<User> userList = userService.findAll();

        model.addAttribute("userList", userList);
        model.addAttribute("order", new Orders());

        return "/service/create-orders";
    }


    @PostMapping("/create-orders")
    public String createOrdersProcess(@ModelAttribute("order")Orders order, HttpSession httpSession){
        CarService carService = getSessionCarService();
        order.setOrderStatus(false);
        order.setCarService(carService);
        ordersService.save(order);
        return "redirect:/service/service-menu";
    }

    @GetMapping("/set-up-a-service")
    public String getSettingPage(Model model){
        CarService carService =getSessionCarService();
        model.addAttribute("carService", carService);
        return "/service/set-up";
    }

    @PostMapping("/set-up-a-service")
    public String updateServiceProcess(@ModelAttribute("carService")CarService carService){
        CarService sessionCarService = getSessionCarService();
        carServiceService.update(sessionCarService, carService);
        return "redirect:/service/service-menu/successful-update";
    }

    @GetMapping("/successful-update")
    public String successfulUpdatePage()
    {
        return "/service/successful-update";
    }

    private CarService getSessionCarService(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CarServiceDetails carServiceDetails = (CarServiceDetails) principal;
        return carServiceDetails.carService();
    }


}
