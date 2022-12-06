package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Owners.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryOwnerRepository extends JpaRepository<Owner, Long> {
}
