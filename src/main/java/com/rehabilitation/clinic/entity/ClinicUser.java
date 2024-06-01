package com.rehabilitation.clinic.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ClinicUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(length = 25)
    private String name;

    @Column(length = 30)
    private String surname;

    @Column(length = 50)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;


    public ClinicUser(){}

    public ClinicUser(String name, String surname, String password, String email)
    {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public ClinicUser(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
