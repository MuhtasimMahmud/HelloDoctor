package com.application.HelloDoctor.repositories;

import com.application.HelloDoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    public Patient findByEmail(String email);

    public Patient findById(int id);
}
