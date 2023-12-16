package com.example.inventoryinstrument.controller.rent;

import com.example.inventoryinstrument.domain.entity.profit.Profit;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import com.example.inventoryinstrument.service.archive.ArchiveService;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.AlignmentService;
import com.example.inventoryinstrument.service.profit.ProfitService;
import com.example.inventoryinstrument.service.rent.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class RentAlignmentController {
    private final AlignmentService alignmentService;
    private final ArchiveService archiveService;
    private final ProfitService profitService;
    private final UserSecurityService userSecurityService;
    private final RentService rentService;

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
        var r = rentService.validationOfRentForSave(rentAlignment, rent, client, archive);
        r.setAlignment(alignment);
        var profit = profitService.createObjectProfit(rent.getDayRent(), alignment.getPrice().getPriceRentOfDay());
        profit.setRent(r);
        profitService.save(profit);
        alignmentService.checkStatusFalse(alignment);
        return "redirect:/alignment";
    }
}
