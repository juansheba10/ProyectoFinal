package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.Users.ThirdParty;
import com.ironhack.ProyectoFinal.models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {


}
