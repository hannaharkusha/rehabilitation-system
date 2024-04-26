package com.rehabilitation.clinic;

import jakarta.persistence.Entity;

@Entity
public class Admin extends ClinicUser
{
    public Admin(){}
    public Admin(String name, String surname, String position, String password)
    {
        super(name, surname, position, password);
    }

}