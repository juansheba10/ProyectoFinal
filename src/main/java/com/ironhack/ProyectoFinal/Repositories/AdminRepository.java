package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
