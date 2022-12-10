package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.TransferMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferMoneyRepository extends JpaRepository<TransferMoney, Long> {
}
