package com.ironhack.ProyectoFinal.models.Users;

import jakarta.persistence.Entity;
import jakarta.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static org.apache.tomcat.util.security.ConcurrentMessageDigest.digest;

@Entity
public class ThirdParty extends User{

    private String hashedKey;

    public ThirdParty(){}


    public ThirdParty(String username, String password, String hashedKey) throws NoSuchAlgorithmException {
        super(username, password);
        setHashedKey(hashedKey);
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(hashedKey.getBytes(StandardCharsets.UTF_8));
        this.hashedKey = DatatypeConverter.printHexBinary(hash);
    }


}
