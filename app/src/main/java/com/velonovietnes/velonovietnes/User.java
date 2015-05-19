package com.velonovietnes.velonovietnes;

public class User {

    String username, email, password;

    public User (String username, String email, String password)
    {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public User (String username, String password)
    {
        this.username=username;
        this.password=password;
        this.email="";
    }



}
