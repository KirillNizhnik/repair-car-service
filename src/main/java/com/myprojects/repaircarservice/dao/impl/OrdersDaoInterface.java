package com.myprojects.repaircarservice.dao.impl;


import com.myprojects.repaircarservice.model.Orders;

import java.util.List;

public interface OrdersDaoInterface {
    List<Orders> findActivityOrders();
    List<Orders> findArchiveOrders();

    List<Orders> findOrdersByUserId(int id);

}
