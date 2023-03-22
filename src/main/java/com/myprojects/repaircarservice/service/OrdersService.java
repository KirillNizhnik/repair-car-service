package com.myprojects.repaircarservice.service;

import com.myprojects.repaircarservice.dao.OrdersDao;
import com.myprojects.repaircarservice.jpa.CarServiceJpa;
import com.myprojects.repaircarservice.jpa.OrdersJpa;
import com.myprojects.repaircarservice.jpa.UserJpa;
import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.model.Orders;
import com.myprojects.repaircarservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersDao ordersDao;
    private final OrdersJpa ordersJpa;

    private final CarServiceJpa carServiceJpa;
    private final UserJpa userJpa;




    @Autowired
    public OrdersService(OrdersDao ordersDao, OrdersJpa ordersJpa, CarServiceJpa carServiceJpa,
                         UserJpa userJpa) {
        this.ordersDao = ordersDao;
        this.ordersJpa = ordersJpa;
        this.carServiceJpa = carServiceJpa;
        this.userJpa = userJpa;
    }
    
    public List<Orders> findActivityOrders(CarService carService){
        return ordersJpa.findByCarServiceAndOrderStatusIsFalse(carService);
    }

    public List<Orders> findArchiveOrders(CarService carService){
        return ordersJpa.findByCarServiceAndOrderStatusIsTrue(carService);
    }

    public List<Orders> findAllUserOrders(User user){
       return ordersDao.findOrdersByUserId(user.getId());
    }


    @Transactional
    public void save(Orders order){
        CarService carService = carServiceJpa.findById(order.getCarService().getId()).get();
        User user = userJpa.findById(order.getUser().getId()).get();

        order.setUser(user);
        order.setCarService(carService);


        ordersJpa.save(order);
    }

}
