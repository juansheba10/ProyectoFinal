package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
