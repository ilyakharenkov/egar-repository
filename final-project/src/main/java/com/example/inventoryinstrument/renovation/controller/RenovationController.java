package com.example.inventoryinstrument.renovation.controller;

import com.example.inventoryinstrument.client.service.UserSecurityService;
import com.example.inventoryinstrument.renovation.service.RenovationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RenovationController {

    private final RenovationService renovationService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/renovation")
    public String findAll(Model model, Principal principal) {

        var renovationList = renovationService.findAll();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("renovationList", renovationList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "renovation";
    }

    @GetMapping("/renovation-not-required-rent")
    public String getRenovationNotRequiredRent(Model model, Principal principal) {
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "renovation-not-required-rent";
    }

    @GetMapping("/renovation-not-required")
    public String getRenovationNotRequired(Model model, Principal principal) {
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "renovation-not-required";
    }

}
