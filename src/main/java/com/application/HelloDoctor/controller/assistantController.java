package com.application.HelloDoctor.controller;

import com.application.HelloDoctor.helper.Message;
import com.application.HelloDoctor.models.Announcement;
import com.application.HelloDoctor.models.Assistant;
import com.application.HelloDoctor.models.User;
import com.application.HelloDoctor.repositories.AnnouncementRepository;
import com.application.HelloDoctor.repositories.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class assistantController {

    @Autowired
    AssistantRepository assistantRepository;

    @Autowired
    AnnouncementRepository announcementRepository;



    @RequestMapping("assistant")
    public String assistant(Model model, Principal principal){

        String userName = principal.getName();
        Assistant assistant = assistantRepository.findByEmail(userName);
        model.addAttribute("assistant", assistant);

        Announcement announcementResult = announcementRepository.findById(1);
        try{
            if(announcementResult == null){
                Announcement announcement = new Announcement(1, assistant.getAnnouncement());
                announcementRepository.save(announcement);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "admin/assistant";
    }


    @RequestMapping("/assistantAnnouncement")
    public String assistantAnnouncementUpdate(@ModelAttribute("assistant") Assistant updatedAnnouncement, HttpSession session){

        Assistant existingAssistant = assistantRepository.findByEmail("assistant@gmail.com");
        existingAssistant.setAnnouncement(updatedAnnouncement.getAnnouncement());

        assistantRepository.save(existingAssistant);

        Announcement announcementResult = announcementRepository.findById(1);
        try{
            if(announcementResult == null){
                Announcement announcement = new Announcement(1, existingAssistant.getAnnouncement());
                announcementRepository.save(announcement);
            }else{
                announcementResult.setAnnouncement(existingAssistant.getAnnouncement());
                announcementRepository.save(announcementResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "admin/assistant";
    }



}
