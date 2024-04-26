package com.rehabilitation.clinic;

import jakarta.persistence.Entity;

@Entity
public class Physiotherapist extends ClinicUser
{
    public Physiotherapist(){}
    public Physiotherapist(String name, String surname, String position, String password)
    {
        super(name, surname, position, password);
    }
}
