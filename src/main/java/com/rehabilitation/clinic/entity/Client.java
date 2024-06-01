package com.rehabilitation.clinic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Client extends ClinicUser
{
    @Column(length = 11)
    private String pesel;

    @Column(length = 11)
    private String phoneNr;


    public Client(){}

    public Client(String name, String surname, String password, String email, String pesel, String phoneNr) {
        super(name, surname, password, email);
        this.pesel = pesel;
        this.phoneNr = phoneNr;
    }

    public Client(String name, String surname, String pesel, String phoneNr) {
        super(name, surname);
        this.pesel = pesel;
        this.phoneNr = phoneNr;
    }

    public String getPesel()
    {
        return pesel;
    }

    public void setPesel(String pesel)
    {
        this.pesel = pesel;
    }

    public String getPhoneNr()
    {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr)
    {
        this.phoneNr = phoneNr;
    }
}
