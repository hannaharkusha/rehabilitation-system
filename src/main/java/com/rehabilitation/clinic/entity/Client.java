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
    @Column(length = 11)
    private String address;

    public Client(){}

    public Client(String name, String surname, String password, String email, String pesel, String phoneNr, String address) {
        super(name, surname, password, email);
        this.pesel = pesel;
        this.phoneNr = phoneNr;
        this.address = address;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
