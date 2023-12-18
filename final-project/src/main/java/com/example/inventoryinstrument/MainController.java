package com.example.inventoryinstrument;

import com.example.inventoryinstrument.client.service.UserSecurityService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
@AllArgsConstructor
public class MainController {

    private final UserSecurityService userSecurityService;

    @GetMapping("/")
    public String startPage(Model model, Principal principal) {
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "main";
    }

    @PostConstruct
    public void test(){
        log.info("Work info");
        log.info("Work info");
        log.info("Work info");
        log.info("Work info");
        log.info("Work info");
    }


}
