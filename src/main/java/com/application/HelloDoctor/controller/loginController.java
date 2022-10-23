package com.application.HelloDoctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

    @RequestMapping("/signin")
    public String login(Model model){
        model.addAttribute("title", "Login");
        return "/login";
    }
}
