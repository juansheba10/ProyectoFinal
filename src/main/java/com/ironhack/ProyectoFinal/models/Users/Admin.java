package com.ironhack.ProyectoFinal.models.Users;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Admin extends User{


    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }
}
