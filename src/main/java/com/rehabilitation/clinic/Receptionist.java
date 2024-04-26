package com.rehabilitation.clinic;

import jakarta.persistence.Entity;

@Entity
public class Receptionist extends ClinicUser
{

    public Receptionist(){}
    public Receptionist(String name, String surname, String position, String password)
    {
        super(name, surname, position, password);
    }
}