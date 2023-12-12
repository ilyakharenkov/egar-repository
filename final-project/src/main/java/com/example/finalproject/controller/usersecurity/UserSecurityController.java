package com.example.finalproject.controller.usersecurity;

import com.example.finalproject.domain.entity.client.Client;
import com.example.finalproject.domain.entity.client.UserSecurity;
import com.example.finalproject.service.client.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserSecurityController {

    private final UserSecurityService userSecurityService;

    @PostMapping("/registration/add")
    public String createUserSecurityAndClient(UserSecurity userSecurity, Client client) {
        userSecurityService.saveUserSecurity(userSecurity, client);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String registration1(Model model, Principal principal) {
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "registration-client";
    }

}
