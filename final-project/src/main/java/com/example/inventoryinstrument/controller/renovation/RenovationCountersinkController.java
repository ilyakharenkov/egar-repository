package com.example.inventoryinstrument.controller.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.exception.ExceptionService;
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
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class RenovationCountersinkController {

    private final CountersinkService countersinkService;
    private final UserSecurityService userSecurityService;
    private final ExceptionService exceptionService;
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
        var renovationAlignment = renovationService.findRenovationByCountersinkId(countersink.getId());
        if (!rent.getCheckStatus()) {
            if (renovationAlignment != null && !rent.getCheckStatus()) {
                if (!renovationAlignment.getCheckStatus() && !rent.getCheckStatus() && !countersink.getCheckStatus()) {
                    renovationAlignment.setCountDay(renovation.getCountDay());
                    renovationAlignment.setPriceDiagnostics(renovation.getPriceDiagnostics());
                    renovationAlignment.setDescriptionResult(renovation.getDescriptionResult());
                    renovationAlignment.setCountersink(countersink);
                    renovationAlignment.setStartRenovation(LocalDate.now());
                    renovationAlignment.setEndRenovation(LocalDate.now().plusDays(renovation.getCountDay()));
                    renovationAlignment.setResultPrice(renovation.getResultPrice());
                    renovationAlignment.setCheckStatus(true);
                    renovationService.update(renovationAlignment);
                } else {
                    System.out.println("Инструмент еще на обслуживании или обслуживание не требуется");
                    return "redirect:/alignment/renovation/{id}";
                }
            } else {
                var ren = renovationService.createObjectRenovation(renovation);
                ren.setCountersink(countersink);
                renovationService.save(ren);
            }
        }
        return "redirect:/countersink";
    }
}
