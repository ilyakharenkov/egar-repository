package com.example.finalproject.controller.usersecurity;

import com.example.finalproject.domain.entity.client.Client;
import com.example.finalproject.domain.entity.client.UserSecurity;
import com.example.finalproject.service.client.UserSecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSecurityController {

    private final UserSecurityService userSecurityService;

    public UserSecurityController(UserSecurityService userSecurityService){
        this.userSecurityService = userSecurityService;
    }

    @GetMapping("/login")
    public String signIn(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUserSecurityAndClient(UserSecurity userSecurity, Client client){
        userSecurityService.saveUserSecurity(userSecurity, client);
        return "redirect:/";
    }

}
