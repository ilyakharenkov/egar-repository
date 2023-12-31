package com.example.inventoryinstrument.alignment.controller;

import com.example.inventoryinstrument.alignment.model.Alignment;
import com.example.inventoryinstrument.price.model.Price;
import com.example.inventoryinstrument.client.service.UserSecurityService;
import com.example.inventoryinstrument.image.service.ImageService;
import com.example.inventoryinstrument.alignment.service.AlignmentService;
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
public class AlignmentController {
    private final AlignmentService alignmentService;
    private final ImageService imageService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/alignment")
    public String findAllAlignment(Model model, Principal principal) {

        var alignmentDtoList = alignmentService.findAll();
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("alignments", alignmentDtoList);
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "alignment";
    }

    @GetMapping("/alignment/{id}")
    public String getAlignmentDetails(@PathVariable(name = "id") Long id, Model model, Principal principal) {

        var alignment = alignmentService.findById(id);
        var userSecurity = userSecurityService.findByPrincipal(principal);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(principal);

        model.addAttribute("alignment", alignment);
        model.addAttribute("listImage", alignment.getImageList());
        model.addAttribute("client", userSecurity);
        model.addAttribute("role", isCheckRoleAdmin);
        return "alignment-details";
    }

    @PostMapping("/alignment/add")
    public String saveAlignment(@RequestParam("listFile") List<MultipartFile> multipartFileList,
                                Alignment alignment,
                                Price price) {
        alignment.setPrice(price);
        alignment.setCheckStatus(true);
        imageService.saveImageAlignment(multipartFileList, alignment);
        alignmentService.save(alignment);
        return "redirect:/alignment";
    }

    @PostMapping("/alignment/delete/{id}")
    public String deleteAlignment(@PathVariable(name = "id") Long id) {

        var alignment = alignmentService.findById(id);

        imageService.deleteFile(alignment.getImageList());
        alignmentService.deleteById(id);
        return "redirect:/alignment";
    }

}
