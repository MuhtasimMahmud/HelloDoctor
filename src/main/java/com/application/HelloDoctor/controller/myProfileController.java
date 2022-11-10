package com.application.HelloDoctor.controller;


import com.application.HelloDoctor.helper.Message;
import com.application.HelloDoctor.models.Patient;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.repositories.PatientRepository;
import com.application.HelloDoctor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class myProfileController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;


    @RequestMapping("/user/myProfile")
    public String myProfile(Model model, Principal principal){

        String userName = principal.getName();
        User user = userRepository.findByEmail(userName);
        model.addAttribute("user", user);
        return "normal/myProfile";
    }


    @RequestMapping(value = "/user/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("user") User updatedUser, HttpSession session){

        User existingUser = userRepository.findByEmail(updatedUser.getEmail());
        Patient patient = patientRepository.findByEmail(updatedUser.getEmail());

        try{
            if(existingUser != null){
                existingUser.setName(updatedUser.getName());
                existingUser.setAge(updatedUser.getAge());
                existingUser.setAddress(updatedUser.getAddress());
                existingUser.setPrescription(updatedUser.getPrescription());

                userRepository.save(existingUser);
                session.setAttribute("message", new Message("Your Profile is Updated", "alert-success"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(patient != null){
                patient.setName(updatedUser.getName());
                patientRepository.save(patient);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "normal/myProfile";
    }


}
