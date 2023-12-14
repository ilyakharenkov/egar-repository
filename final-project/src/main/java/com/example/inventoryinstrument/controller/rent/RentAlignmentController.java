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
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        model.addAttribute("alignment", alignmentService.findById(id));
        model.addAttribute("listImage", alignmentService.findById(id).getImageList());
        return "alignment-rent";
    }

    @PostMapping("/alignment/rent/add/{id}")
    public String createRentAlignment(@PathVariable(name = "id") Long id, Principal principal, Rent rent) {
        try {
            var us = userSecurityService.findByPrincipal(principal);
            var alignment = alignmentService.findById(id);
            var archive = archiveService.createObjectArchive(us.getClient(), rent, alignment.getId(), alignment.getName());
            var rentAlignment = rentService.findRentByAlignmentId(alignment.getId());
            if (rentAlignment != null && !rentAlignment.getCheckStatus()) {
                rentAlignment.setDayRent(rent.getDayRent());
                rentAlignment.setStartRental(LocalDate.now());
                rentAlignment.setEndRental(rentService.timeOutRent(LocalDate.now(), rent.getDayRent()));
                rentAlignment.setCheckStatus(true);
                rentAlignment.setAlignment(alignment);
                rentAlignment.setClient(us.getClient());
                rentAlignment.setArchive(archive);
                rentService.update(rentAlignment);
            } else {
                Profit profit = profitService.createObjectProfit(rent.getDayRent(), alignment.getPrice().getPriceRentOfDay());
                Rent r = Rent.builder()
                        .dayRent(rent.getDayRent())
                        .startRental(LocalDate.now())
                        .endRental(rentService.timeOutRent(LocalDate.now(), rent.getDayRent()))
                        .checkStatus(true)
                        .alignment(alignment)
                        .client(us.getClient())
                        .archive(archive)
                        .build();
                profit.setRent(r);
                profitService.save(profit);
                rentService.save(r);
            }
            alignmentService.checkStatusFalse(alignment);
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println(e.getMessage());
            return "redirect:/alignment/rent/{id}";
        }
        return "redirect:/alignment";
    }
}
