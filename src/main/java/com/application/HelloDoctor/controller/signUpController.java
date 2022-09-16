package com.application.HelloDoctor.controller;

import com.application.HelloDoctor.helper.Message;
import com.application.HelloDoctor.models.Assistant;
import com.application.HelloDoctor.models.Doctor;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.repositories.AssistantRepository;
import com.application.HelloDoctor.repositories.DoctorRepository;
import com.application.HelloDoctor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class signUpController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AssistantRepository assistantRepository;


    @RequestMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("title", "Register Account");
        model.addAttribute("user", new User());
        return "signUp";
    }


    @RequestMapping("/adminSignUp")
    public String abc(Model model){
        model.addAttribute("title", "Admin Registration");
        model.addAttribute("user", new User());
        return "admin/adminSignUp";
    }



    //handler for registering user
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, Model model, HttpSession session){

        // jodi user.role == doctor / user.role == assistant hoy then eikhane alada alada database e jabe + user database eu jabe

        String return_page = "";
        Doctor doctor;
        Assistant assistant;

        try{

            if(user.getRole().equals("##DOCTOR")){
                user.setRole("ROLE_DOCTOR");
                doctor = new Doctor(user.getId(), user.getEmail(), user.getName());
                Doctor Dresult = this.doctorRepository.save(doctor);
                return_page = "admin/adminSignUp";

            }else if(user.getRole().equals("$$ASSISTANT")){
                user.setRole("ROLE_ASSISTANT");
                assistant = new Assistant(user.getId(), user.getEmail(), user.getName(), "");
                Assistant Aresult = this.assistantRepository.save(assistant);
                return_page = "admin/adminSignUp";

            }else{
                user.setRole("ROLE_USER");
                return_page = "signUp";
            }

            user.setImageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            User Uresult = this.userRepository.save(user);


            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));

            return return_page;


        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));

            return return_page;
        }



    }



}
