package com.rehabilitation.clinic;

public abstract class User
{

    private int userId;
    private String name;
    private String surname;
    private String position;
    //private Account account;

    public User(String name, String surname, String position)
    {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public int getUserIdId()
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

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }


}
