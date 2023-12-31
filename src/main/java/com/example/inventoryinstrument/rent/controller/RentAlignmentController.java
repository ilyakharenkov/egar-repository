package com.example.inventoryinstrument.rent.controller;

import com.example.inventoryinstrument.rent.model.Rent;
import com.example.inventoryinstrument.archive.service.ArchiveService;
import com.example.inventoryinstrument.client.service.UserSecurityService;
import com.example.inventoryinstrument.alignment.service.AlignmentService;
import com.example.inventoryinstrument.profit.service.ProfitService;
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
public class RentAlignmentController {
    private final AlignmentService alignmentService;
    private final ArchiveService archiveService;
    private final ProfitService profitService;
    private final UserSecurityService userSecurityService;
    private final RentService rentService;

    private final RenovationService renovationService;

    @GetMapping("/alignment/rent/{id}")
    public String getInfoRentAlignment(@PathVariable(name = "id") Long id, Model model, Principal principal) {

        var alignment = alignmentService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("alignment", alignment);
        model.addAttribute("listImage", alignment.getImageList());
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "alignment-rent";
    }

    @PostMapping("/alignment/rent/add/{id}")
    public String createRentAlignment(@PathVariable(name = "id") Long id, Principal principal, Rent rent) {
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var client = userSecurity.getClient();
        var alignment = alignmentService.findById(id);
        var archive = archiveService.createObjectArchive(client, rent, alignment.getId(), alignment.getName());
        var rentAlignment = rentService.findRentByAlignmentId(alignment.getId());
        var renovationAlignment = renovationService.findRenovationByAlignmentId(alignment.getId());

        //Проверка инструмента в аренде он или нет.
        if(rentAlignment != null && rentAlignment.getCheckStatus()){
            return "redirect:/renovation-not-required-rent";
        }

        //Проверка инструмента на осблуживании он или нет.
        if(alignment.getCheckStatus() && renovationAlignment == null || alignment.getCheckStatus() && !renovationAlignment.getCheckStatus()){
            var r = rentService.validationOfRentForSave(rentAlignment, rent, client, archive);
            r.setAlignment(alignment);
            rentService.save(r);
            var profit = profitService.createObjectProfit(rent.getDayRent(), alignment.getPrice().getPriceRentOfDay());
            profit.setRent(r);
            profitService.save(profit);
        } else {
            return "redirect:/renovation-not-required";
        }

        alignmentService.checkStatusFalse(alignment);
        return "redirect:/alignment";
    }
}
