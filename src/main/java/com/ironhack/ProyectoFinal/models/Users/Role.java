package com.ironhack.ProyectoFinal.models.Users;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String Role;
    @ManyToOne
    private User user;


    public Role(){}

    public Role(String role, User user) {
        Role = role;
        this.user = user;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
