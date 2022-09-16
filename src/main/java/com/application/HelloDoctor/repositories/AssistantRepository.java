package com.application.HelloDoctor.repositories;

import com.application.HelloDoctor.models.Assistant;
import com.application.HelloDoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistantRepository extends JpaRepository<Assistant, Integer> {

    public Assistant findByEmail(String email);

}
