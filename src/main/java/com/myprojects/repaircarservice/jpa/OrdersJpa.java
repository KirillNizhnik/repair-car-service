package com.myprojects.repaircarservice.jpa;

import com.myprojects.repaircarservice.model.CarService;
import com.myprojects.repaircarservice.model.Orders;
import com.myprojects.repaircarservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrdersJpa extends JpaRepository<Orders, Integer> {
    List<Orders> findByCarService(CarService carService);

    List<Orders> findByCarServiceAndOrderStatusIsFalse(CarService carService);

    List<Orders> findByCarServiceAndOrderStatusIsTrue(CarService  carService);

    List<Orders> findAllByUser(User user);

}
