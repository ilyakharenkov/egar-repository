package com.example.inventoryinstrument.controller.rent;

import com.example.inventoryinstrument.domain.entity.rent.Rent;
import com.example.inventoryinstrument.service.archive.ArchiveService;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.instrument.CountersinkService;
import com.example.inventoryinstrument.service.profit.ProfitService;
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
public class RentCountersinkController {

    private final CountersinkService countersinkService;
    private final ArchiveService archiveService;
    private final ProfitService profitService;
    private final UserSecurityService userSecurityService;
    private final RentService rentService;

    private final RenovationService renovationService;

    @GetMapping("/countersink/rent/{id}")
    public String getInfoRentCountersink(@PathVariable("id") Long id, Model model, Principal principal) {

        var countersink = countersinkService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("countersink", countersink);
        model.addAttribute("listImage", countersink.getImageList());
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "countersink-rent";
    }

    @PostMapping("/countersink/rent/add/{id}")
    public String createRentCountersink(@PathVariable(name = "id") Long id, Principal principal, Rent rent) {
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var client = userSecurity.getClient();
        var countersink = countersinkService.findById(id);
        var archive = archiveService.createObjectArchive(client, rent, countersink.getId(), countersink.getName());
        var rentCountersink = rentService.findRentByCountersinkId(countersink.getId());
        var renovationCountersink = renovationService.findRenovationByCountersinkId(countersink.getId());

        //Проверка инструмента в аренде он или нет.
        if (rentCountersink != null && rentCountersink.getCheckStatus()) {
            return "redirect:/renovation-not-required-rent";
        }
        //Проверка инструмента на осблуживании он или нет.
        if (countersink.getCheckStatus() && renovationCountersink == null || countersink.getCheckStatus() && !renovationCountersink.getCheckStatus()) {
            var r = rentService.validationOfRentForSave(rentCountersink, rent, client, archive);
            r.setCountersink(countersink);
            rentService.save(r);

            var profit = profitService.createObjectProfit(rent.getDayRent(), countersink.getPrice().getPriceRentOfDay());
            profit.setRent(r);
            profitService.save(profit);
        } else {
            return "redirect:/renovation-not-required";
        }

        countersinkService.checkStatusFalse(countersink);
        return "redirect:/countersink";
    }


}
