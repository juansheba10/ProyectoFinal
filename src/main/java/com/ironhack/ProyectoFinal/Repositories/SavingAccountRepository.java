package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {
}
