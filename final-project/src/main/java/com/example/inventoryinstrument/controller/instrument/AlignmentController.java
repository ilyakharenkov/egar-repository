package com.example.inventoryinstrument.controller.instrument;

import com.example.inventoryinstrument.domain.entity.client.UserSecurity;
import com.example.inventoryinstrument.domain.entity.instrument.Alignment;
import com.example.inventoryinstrument.domain.entity.price.Price;
import com.example.inventoryinstrument.service.client.UserSecurityService;
import com.example.inventoryinstrument.service.exception.ExceptionService;
import com.example.inventoryinstrument.service.image.ImageService;
import com.example.inventoryinstrument.service.instrument.AlignmentService;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ExceptionService exceptionService;
    private final ImageService imageService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/alignment")
    public String findAllAlignment(Model model, Principal principal) {

        var a = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var alignmentDtoList = alignmentService.findAll();
        var userSecurity = userSecurityService.findByPrincipal(a);
        var isCheckRoleAdmin = userSecurityService.findByRoleAdmin(a);

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
        try {
            alignment.setPrice(price);
            alignment.setCheckStatus(true);
            imageService.saveImageAlignment(multipartFileList, alignment);
            alignmentService.save(alignment);
        } catch (ConstraintViolationException e) {
            exceptionService.methodValidationInstrumentException(alignment);
        }
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
