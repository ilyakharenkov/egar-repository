package com.example.inventoryinstrument.controller.renovation;

import com.example.inventoryinstrument.domain.entity.renovation.Renovation;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.AlignmentService;
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
        if (!rent.getCheckStatus()) {
            if (renovationAlignment != null && !rent.getCheckStatus()) {
                if (!renovationAlignment.getCheckStatus() && !rent.getCheckStatus() && !alignment.getCheckStatus()) {
                    renovationAlignment.setCountDay(renovation.getCountDay());
                    renovationAlignment.setPriceDiagnostics(renovation.getPriceDiagnostics());
                    renovationAlignment.setDescriptionResult(renovation.getDescriptionResult());
                    renovationAlignment.setAlignment(alignment);
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
                ren.setAlignment(alignment);
                renovationService.save(ren);
            }
        }
        return "redirect:/alignment";
    }

}
