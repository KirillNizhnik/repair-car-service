package com.myprojects.repaircarservice.dao;

import com.myprojects.repaircarservice.dao.impl.UserDaoInterface;
import com.myprojects.repaircarservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDao implements UserDaoInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findBySurname(String surname) {

        String sql = "SELECT * FROM `user` WHERE `surname` LIKE ?";
        List<User> userList = jdbcTemplate.query(sql,
                new Object[]{surname + "%"},
                new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class));
        return userList;
    }
}
