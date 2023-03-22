package com.myprojects.repaircarservice.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_latter")
    private String orderLatter;

    @Column(name = "order_status")
    private boolean orderStatus;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id")
    private CarService carService;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderLatter='" + orderLatter + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }


}
