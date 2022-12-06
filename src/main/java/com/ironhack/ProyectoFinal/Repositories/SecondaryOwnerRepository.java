package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryOwnerRepository extends JpaRepository<SecondaryOwner, Long> {
}
