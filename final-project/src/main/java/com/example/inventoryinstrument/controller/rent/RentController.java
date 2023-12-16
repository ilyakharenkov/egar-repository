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

        var rentList = rentService.findAllRent();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("rentList", rentList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "rent";
    }

    //Показать весь арендованный инструмент.
    @GetMapping("/rent-rent")
    public String getFindAllRent(Model model, Principal principal) {

        var rentList = rentService.findAllRent();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("findAllRent", rentList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "rent-rent";
    }

    //Показать весь освобожденный инструмент который был в аренде.
    @GetMapping("/rent-free")
    public String getFindAllFree(Model model, Principal principal) {

        var findAllFree = rentService.findAllFree();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("findAllFree", findAllFree);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "rent-free";
    }

    //Весь свободный инструмент.
    @GetMapping("/instrument-free")
    public String getFreeAllInstrument(Model model, Principal principal){

        var alignmentDtoList = alignmentService.findFreeAlignment();
        var countersinkDtoList = countersinkService.findFreeCountersink();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("alignmentList", alignmentDtoList);
        model.addAttribute("countersinkList", countersinkDtoList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "instrument-free";
    }

    //Инструмент который арендован в данный момент у пользователя.
    @GetMapping("/profile-rent")
    public String getFindRentByClient(Model model, Principal principal) {

        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);
        var rentList = rentService.findRentByClient(userSecurity.getClient().getId());

        model.addAttribute("rentList", rentList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "profile-rent";
    }

    @GetMapping("/rent/sort-start")
    public String sortStartRent(Model model, Principal principal){

        var rentList = rentService.sortByStartRental();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("startRent", rentList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "rent-sort-start";
    }

    @GetMapping("/rent/sort-end")
    public String sortEndRent(Model model, Principal principal){

        var rentList = rentService.sortByEndRental();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("endRent", rentList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "rent-sort-end";
    }


}
