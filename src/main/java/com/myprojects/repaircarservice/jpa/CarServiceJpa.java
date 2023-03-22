package com.myprojects.repaircarservice.jpa;

import com.myprojects.repaircarservice.model.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CarServiceJpa extends JpaRepository<CarService, Integer> {
    Optional<CarService> findByLogin(String login);

}
