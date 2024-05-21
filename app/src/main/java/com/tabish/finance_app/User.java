package com.tabish.finance_app;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class User {
    private String username;

    private String First_name;
    private String Last_Name;
    private String password;

    public User(){

    };
    public User(String username, String first_name, String last_Name, String password) {
        this.username = username;
        First_name = first_name;
        Last_Name = last_Name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
