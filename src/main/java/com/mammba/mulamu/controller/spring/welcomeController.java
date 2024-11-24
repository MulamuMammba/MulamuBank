package com.mammba.mulamu.controller.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class welcomeController {
    @GetMapping("/ref4regfregf")
    public String welcome(Model model){
        model.addAttribute("message","Hello web world");
        return "welcome";
    }
}


