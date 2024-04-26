package com.rehabilitation.clinic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Client extends ClinicUser
{
    @Column(length = 11)
    private String pesel;
    private String phoneNr;
    private String email;
    private String address;

    public Client(){}
    public Client(String name, String surname, String position, String password)
    {
        super(name, surname, position, password);
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
