package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
