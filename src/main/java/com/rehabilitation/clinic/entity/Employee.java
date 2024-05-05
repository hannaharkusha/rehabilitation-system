package com.rehabilitation.clinic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Employee extends ClinicUser
{
    @Column(length = 15, nullable = false)
    private String position;

    public Employee(){}
    public Employee(String name, String surname, String password, String position)
    {
        super(name, surname, password);
        this.position = position;
    }


    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }
}
