package com.myprojects.repaircarservice.jpa;


import com.myprojects.repaircarservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserJpa extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
