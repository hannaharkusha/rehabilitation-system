package com.rehabilitation.clinic.entity;

import jakarta.persistence.*;
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(length = 30, nullable = false)
    private String name;
    @Column(nullable = false)
    private float price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Service() {}

    public Service(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
