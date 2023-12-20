package com.example.inventoryinstrument.countersink.controller;

import com.example.inventoryinstrument.countersink.model.Countersink;
import com.example.inventoryinstrument.price.model.Price;
import com.example.inventoryinstrument.client.service.UserSecurityService;
import com.example.inventoryinstrument.image.service.ImageService;
import com.example.inventoryinstrument.countersink.service.CountersinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CountersinkController {

    private final CountersinkService countersinkService;
    private final ImageService imageService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/countersink")
    public String findAllCountersink(Model model, Principal principal) {

        var countersinkDtoList = countersinkService.findAll();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("countersinks", countersinkDtoList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "countersink";
    }

    @GetMapping("/countersink/{id}")
    public String getCountersinkDetails(@PathVariable("id") Long id, Model model, Principal principal) {

        var countersink = countersinkService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("countersink", countersink);
        model.addAttribute("listImage", countersink.getImageList());
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "countersink-details";
    }

    @PostMapping("/countersink/add")
    public String saveCountersink(@RequestParam("listFile") List<MultipartFile> multipartFileList,
                                  Countersink countersink,
                                  Price price) {
        countersink.setCheckStatus(true);
        countersink.setPrice(price);
        imageService.saveImageCountersink(multipartFileList, countersink);
        countersinkService.save(countersink);
        return "redirect:/countersink";
    }

    @PostMapping("/countersink/delete/{id}")
    public String deleteCountersink(@PathVariable("id") Long id) {
        var countersink = countersinkService.findById(id);
        imageService.deleteFile(countersink.getImageList());
        countersinkService.deleteById(id);
        return "redirect:/countersink";
    }

}
