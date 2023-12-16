package com.example.inventoryinstrument.controller.renovation;

import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.renovation.RenovationService;
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
    public String findAll(Model model, Principal principal){

        var renovationList = renovationService.findAll();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("renovationList", renovationList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "renovation";
    }

}
