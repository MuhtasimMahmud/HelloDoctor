package com.application.HelloDoctor.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("title", "Hello Doctor");
        return "home";
    }

}
