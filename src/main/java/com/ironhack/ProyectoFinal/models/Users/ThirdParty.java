package com.ironhack.ProyectoFinal.models.Users;

import jakarta.persistence.Entity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
@Entity
public class ThirdParty extends User{

    private String hashedKey;

    public ThirdParty(){}


    public ThirdParty(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
