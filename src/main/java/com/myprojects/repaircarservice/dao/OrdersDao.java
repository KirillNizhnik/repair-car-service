package com.myprojects.repaircarservice.dao;

import com.myprojects.repaircarservice.dao.impl.OrdersDaoInterface;
import com.myprojects.repaircarservice.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(readOnly = true)
public class OrdersDao implements OrdersDaoInterface {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrdersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Orders> findActivityOrders() {
        String sql = "SELECT * FROM orders WHERE order_status = 0";
        List<Orders> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
        return orders;
    }

    @Override
    public List<Orders> findArchiveOrders() {
        String sql = "SELECT * FROM orders WHERE order_status = 1";
        List<Orders> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
        return orders;
    }

    @Override
    public List<Orders> findOrdersByUserId(int id) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(Orders.class));
    }
}
