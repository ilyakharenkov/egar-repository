package com.example.finalproject.controller.rental;

import com.example.finalproject.domain.entity.rent.Rent;
import com.example.finalproject.service.instrument.AlignmentService;
import com.example.finalproject.service.profit.ProfitService;
import com.example.finalproject.service.rent.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class AlignmentRentalController {

    private final AlignmentService alignmentService;
    private final RentService rentService;

    @GetMapping("/alignment/rental/{id}")
    public String getRental(@PathVariable("id") Long id, Model model){
        model.addAttribute("alignmentRental", alignmentService.getAlignmentById(id));
        return "rental";
    }

    @PostMapping("/alignment/rental/add/{id}")
    public String addRental(@PathVariable(name = "id") Long id, Principal principal, Rent rent){
        rentService.save(principal, rent, alignmentService.getAlignmentById(id));
        alignmentService.checkStatus(alignmentService.getAlignmentById(id));
        return "redirect:/alignment";
    }

}
