package com.application.HelloDoctor.controller;

import com.application.HelloDoctor.helper.Message;
import com.application.HelloDoctor.models.Announcement;
import com.application.HelloDoctor.models.Assistant;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.models.Patient;
import com.application.HelloDoctor.repositories.AnnouncementRepository;
import com.application.HelloDoctor.repositories.AssistantRepository;
import com.application.HelloDoctor.repositories.PatientRepository;
import com.application.HelloDoctor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
public class appointmentBookingController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;



    @RequestMapping("/user/appointmentBooking")
    public String appointmentBooking(Model model, Principal principal){

        String userName = principal.getName();

        User user = userRepository.findByEmail(userName);
        model.addAttribute("user", user);


        Announcement announcementResult = announcementRepository.findById(1);
        model.addAttribute("announcement", announcementResult);

        return "normal/appointmentBooking";
    }


    // handler for registering patients_list
    @RequestMapping(value = "/booked_appointment", method = RequestMethod.POST)
    public String book_patient(@ModelAttribute User user, Model model, HttpSession session){


        Patient patient = new Patient();
        patient.setEmail(user.getEmail());
        patient.setName(user.getName());
        patient.setDate(user.getAppointment_date());


        Patient result = this.patientRepository.save(patient);
        session.setAttribute("message", new Message("Successfully Booked Your appointment.", "alert-success"));

        Announcement announcementResult = announcementRepository.findById(1);
        model.addAttribute("announcement", announcementResult);

        return "normal/appointmentBooking";
    }

}
