package com.myprojects.repaircarservice.dao.impl;

import com.myprojects.repaircarservice.model.User;

import java.util.List;

public interface UserDaoInterface {
    List<User> findBySurname(String surname);
}
