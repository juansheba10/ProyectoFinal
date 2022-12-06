package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
