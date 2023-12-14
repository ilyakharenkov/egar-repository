package com.example.inventoryinstrument.controller.rent;

import com.example.inventoryinstrument.domain.entity.profit.Profit;
import com.example.inventoryinstrument.domain.entity.rent.Rent;
import com.example.inventoryinstrument.service.archive.ArchiveService;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.CountersinkService;
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
public class RentCountersinkController {

    private final CountersinkService countersinkService;
    private final ArchiveService archiveService;
    private final ProfitService profitService;
    private final UserSecurityService userSecurityService;
    private final RentService rentService;

    @GetMapping("/countersink/rent/{id}")
    public String getInfoRentCountersink(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        model.addAttribute("countersink", countersinkService.findById(id));
        model.addAttribute("listImage", countersinkService.findById(id).getImageList());
        return "countersink-rent";
    }

    //Сохранение аренды.
    @PostMapping("/countersink/rent/add/{id}")
    public String createRentCountersink(@PathVariable(name = "id") Long id, Principal principal, Rent rent) {
        try {
            var us = userSecurityService.findByPrincipal(principal);
            var countersink = countersinkService.findById(id);
            var archive = archiveService.createObjectArchive(us.getClient(), rent, countersink.getId(), countersink.getName());
            var rentCountersink = rentService.findRentByCountersinkId(countersink.getId());
            if (rentCountersink != null && !rentCountersink.getCheckStatus()) {
                rentCountersink.setDayRent(rent.getDayRent());
                rentCountersink.setStartRental(LocalDate.now());
                rentCountersink.setEndRental(rentService.timeOutRent(LocalDate.now(), rent.getDayRent()));
                rentCountersink.setCheckStatus(true);
                rentCountersink.setCountersink(countersink);
                rentCountersink.setClient(us.getClient());
                rentCountersink.setArchive(archive);
                rentService.update(rentCountersink);
            } else {
                Profit profit = profitService.createObjectProfit(rent.getDayRent(), countersink.getPrice().getPriceRentOfDay());
                Rent r = Rent.builder()
                        .dayRent(rent.getDayRent())
                        .startRental(LocalDate.now())
                        .endRental(rentService.timeOutRent(LocalDate.now(), rent.getDayRent()))
                        .checkStatus(true)
                        .countersink(countersink)
                        .client(us.getClient())
                        .archive(archive)
                        .build();
                profit.setRent(r);
                profitService.save(profit);
                rentService.save(r);
            }
            countersinkService.checkStatusFalse(countersink);
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println(e.getMessage());
            return "redirect:/countersink/rent/{id}";
        }
        return "redirect:/countersink";
    }

}
