package com.application.HelloDoctor.controller;


import com.application.HelloDoctor.helper.Message;
import com.application.HelloDoctor.models.Patient;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.repositories.PatientRepository;
import com.application.HelloDoctor.repositories.UserRepository;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class adminController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/admin")
    public String adminView(Model model){

        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "admin/admin";
    }


    @RequestMapping("/adminTreatmentDone/{id}")
    public String done(@PathVariable("id") int id){

        Patient patient = patientRepository.findById(id);
        try{
            if(patient != null){
                patientRepository.delete(patient);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/admin";
    }

    @RequestMapping("/adminPrescription/{email}")
    public String prescriptionEdit(@PathVariable("email") String email, Model model){

        User user = userRepository.findByEmail(email);
        model.addAttribute("user", user);

        return "updatePrescription";
    }

    @Transactional
    @RequestMapping(value = "/adminUpdatePrescription", method = RequestMethod.POST)
    public String updatePrescription(@ModelAttribute("user") User updatedUser, HttpSession session){

        User existingUser = userRepository.findByEmail(updatedUser.getEmail());

        try{
            if(existingUser != null){
                existingUser.setAge(updatedUser.getAge());
                existingUser.setPrescription(updatedUser.getPrescription());

                userRepository.save(existingUser);
                session.setAttribute("message", new Message("Patient's profile is Updated", "alert-success"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "updatePrescription";
    }





}
