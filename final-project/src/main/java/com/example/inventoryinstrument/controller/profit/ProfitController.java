package com.example.inventoryinstrument.controller.profit;

import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.profit.ProfitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ProfitController {

    private final ProfitService profitService;

    private final UserSecurityService userSecurityService;

    @GetMapping("/profit")
    public String getProfit(Model model, Principal principal){
        model.addAttribute("profitList", profitService.findAll());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        model.addAttribute("sumProfit", profitService.findSumProfit());
        return "/profit";
    }

}