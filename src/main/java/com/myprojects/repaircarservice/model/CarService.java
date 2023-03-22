package com.myprojects.repaircarservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_service")
@Data
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name = "name")
    private String name;


    @Column(name = "phone_numbers")
    private String phoneNumbers;


    @Column(name = "email")
    private String email;

    @Column(name = "role")
    String role;


    @Column(name = "login")
    private String login;


    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "carService")
    private List<Orders> ordersList;


    @Override
    public String toString() {
        return "CarService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
