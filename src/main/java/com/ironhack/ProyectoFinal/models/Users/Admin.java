package com.ironhack.ProyectoFinal.models.Users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{


    public Admin() {
    }

    public Admin(String name, String password) {
        super(name);
    }

}
