package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.StudentCheckingRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    public StudentChecking addAccount(StudentChecking studentChecking) {
        return studentCheckingRepository.save(studentChecking);
    }
}
