package com.application.HelloDoctor.repositories;

import com.application.HelloDoctor.models.Doctor;
import com.application.HelloDoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findByEmail(String email);

    public Doctor findById(int id);
}
