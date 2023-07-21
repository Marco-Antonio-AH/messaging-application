package com.example.ccln2;

public class User {
    public String phoneNumber;
    public String password;

    public User() {
        // Constructor vacío requerido para Firebase
    }

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}