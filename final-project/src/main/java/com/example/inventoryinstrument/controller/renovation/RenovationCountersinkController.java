package com.example.inventoryinstrument.controller.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.CountersinkService;
import com.example.inventoryinstrument.service.renovation.RenovationService;
import com.example.inventoryinstrument.service.rent.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RenovationCountersinkController {

    private final CountersinkService countersinkService;
    private final UserSecurityService userSecurityService;
    private final RentService rentService;
    private final RenovationService renovationService;

    @GetMapping("/countersink/renovation/{id}")
    public String getRenovationCountersink(@PathVariable("id") Long id, Model model, Principal principal) {

        var countersink = countersinkService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("countersink", countersink);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "countersink-renovation";
    }

    @PostMapping("/countersink/renovation/add/{id}")
    public String addRenovationCountersink(@PathVariable("id") Long id, Renovation renovation) {

        var countersink = countersinkService.findById(id);
        var rent = rentService.findRentByCountersinkId(countersink.getId());
        var renovationCountersink = renovationService.findRenovationByCountersinkId(countersink.getId());

        //Проверка нструмента на обслуживании он или нет.
        if (renovationCountersink != null && renovationCountersink.getCheckStatus()) {
            return "redirect:/renovation-not-required";
        }

        //Проверка что инструмент не в аренде.
        if (!countersink.getCheckStatus() && !rent.getCheckStatus()) {
            var r = renovationService.validationOfRenovationForSave(renovationCountersink, renovation);
            r.setCountersink(countersink);
            renovationService.save(r);
        } else {
            return "redirect:/renovation-not-required-rent";
        }
        return "redirect:/countersink";
    }
}
