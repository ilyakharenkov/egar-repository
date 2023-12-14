package com.example.inventoryinstrument.controller.rent;

import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.AlignmentService;
import com.example.inventoryinstrument.service.instrument.CountersinkService;
import com.example.inventoryinstrument.service.rent.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RentController {

    private final RentService rentService;

    private final AlignmentService alignmentService;

    private final CountersinkService countersinkService;
    private final UserSecurityService userSecurityService;

    //Показать весь инструмент который был или арендован в данный момент.
    @GetMapping("/rent")
    public String getFindAll(Model model, Principal principal) {
        model.addAttribute("rentList", rentService.findAll());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "rent";
    }

    //Показать весь арендованный инструмент.
    @GetMapping("/rent-rent")
    public String getFindAllRent(Model model, Principal principal) {
        model.addAttribute("findAllRent", rentService.findAllRent());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "rent-rent";
    }

    //Показать весь освобожденный инструмент который был в аренде.
    @GetMapping("/rent-free")
    public String getFindAllFree(Model model, Principal principal) {
        model.addAttribute("findAllFree", rentService.findAllFree());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "rent-free";
    }

    //Весь свободный инструмент.
    @GetMapping("/instrument-free")
    public String getFreeAllInstrument(Model model, Principal principal){
        model.addAttribute("alignmentList", alignmentService.findFreeAlignment());
        model.addAttribute("countersinkList", countersinkService.findFreeCountersink());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "instrument-free";
    }

    //Инструмент который арендован в данный момент у пользователя.
    @GetMapping("/profile-rent")
    public String getFindRentByClient(Model model, Principal principal) {
        var us = userSecurityService.findByPrincipal(principal);
        model.addAttribute("rentList", rentService.findRentByClient(us.getClient().getId()));
        model.addAttribute("client", us);
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "profile-rent";
    }

    @GetMapping("/rent/sort-start")
    public String sortStartRent(Model model, Principal principal){
        model.addAttribute("startRent", rentService.sortByStartRental());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "rent-sort-start";
    }

    @GetMapping("/rent/sort-end")
    public String sortEndRent(Model model, Principal principal){
        model.addAttribute("endRent", rentService.sortByEndRental());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "rent-sort-end";
    }


}
