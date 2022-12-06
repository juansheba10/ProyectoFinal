package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
