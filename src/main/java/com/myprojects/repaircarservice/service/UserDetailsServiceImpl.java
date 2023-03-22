package com.myprojects.repaircarservice.service;

import com.myprojects.repaircarservice.jpa.UserJpa;
import com.myprojects.repaircarservice.model.User;
import com.myprojects.repaircarservice.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private  final UserJpa userJpa;

    @Autowired
    public UserDetailsServiceImpl(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userJpa.findByLogin(username);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return new UserDetailsImpl(userOptional.get());
    }
}
