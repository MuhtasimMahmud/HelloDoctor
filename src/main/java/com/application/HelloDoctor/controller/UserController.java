package com.application.HelloDoctor.controller;

import com.application.HelloDoctor.models.Patient;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.repositories.PatientRepository;
import com.application.HelloDoctor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/user_dashboard")
    public String dashboard(Model model, Principal principal){

        String userName = principal.getName();
        User user = userRepository.findByEmail(userName);

        try{
            Patient patient = patientRepository.findByEmail(user.getEmail());
            if(patient != null){
                user.setAppointmentStatus("You have an appointment on " + patient.getDate());
            }else{
                user.setAppointmentStatus("You have no appointment Booked.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }
}
