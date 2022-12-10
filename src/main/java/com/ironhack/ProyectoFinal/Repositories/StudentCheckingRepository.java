package com.ironhack.ProyectoFinal.Repositories;

import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
