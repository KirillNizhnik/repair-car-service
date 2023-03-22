package com.myprojects.repaircarservice.service;

import com.myprojects.repaircarservice.dao.UserDao;
import com.myprojects.repaircarservice.jpa.UserJpa;
import com.myprojects.repaircarservice.model.Orders;
import com.myprojects.repaircarservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements Register<User> {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserJpa userJpa;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserJpa userJpa, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userJpa = userJpa;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers(){return userJpa.findAll();}

    public Optional<User> findByLogin(String login){
        return userJpa.findByLogin(login);
    }

    public List<User> findAll(){return userJpa.findAll();}

    public List<User> findUsersBySurname(String surname){return userDao.findBySurname(surname);}

    public List<Orders> getOrders(int userId) {
        Optional<User> optionalUser = userJpa.findById(userId);
        User user = optionalUser.orElseThrow(RuntimeException::new);
        return user.getOrdersList();}


    @Transactional
    @Override
    public void register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(ROLE_USER);
        userJpa.save(user);
    }
}
