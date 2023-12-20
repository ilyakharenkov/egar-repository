package com.example.inventoryinstrument.renovation.controller;

import com.example.inventoryinstrument.renovation.model.Renovation;
import com.example.inventoryinstrument.client.service.UserSecurityService;
import com.example.inventoryinstrument.alignment.service.AlignmentService;
import com.example.inventoryinstrument.renovation.service.RenovationService;
import com.example.inventoryinstrument.rent.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RenovationAlignmentController {
    private final AlignmentService alignmentService;
    private final UserSecurityService userSecurityService;
    private final RenovationService renovationService;
    private final RentService rentService;

    @GetMapping("/alignment/renovation/{id}")
    public String getRenovationAlignment(@PathVariable("id") Long id, Model model, Principal principal) {

        var alignment = alignmentService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("alignment", alignment);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "alignment-renovation";
    }

    @PostMapping("/alignment/renovation/add/{id}")
    public String addRenovationAlignment(@PathVariable("id") Long id, Renovation renovation) {

        var alignment = alignmentService.findById(id);
        var rent = rentService.findRentByAlignmentId(alignment.getId());
        var renovationAlignment = renovationService.findRenovationByAlignmentId(alignment.getId());

        //Проверка нструмента на обслуживании он или нет.
        if (renovationAlignment != null && renovationAlignment.getCheckStatus()) {
            return "redirect:/renovation-not-required";
        }

        //Проверка что инструмент не в аренде.
        if (!alignment.getCheckStatus() && !rent.getCheckStatus()) {
            var r = renovationService.validationOfRenovationForSave(renovationAlignment, renovation);
            r.setAlignment(alignment);
            renovationService.save(r);
        } else {
            return "redirect:/renovation-not-required-rent";
        }
        return "redirect:/alignment";
    }

}
