package com.example.finalproject.controller;

import com.example.finalproject.service.client.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserSecurityService userSecurityService;

    @GetMapping("/")
    public String startPage(Model model, Principal principal) {
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        return "main";
    }

}
