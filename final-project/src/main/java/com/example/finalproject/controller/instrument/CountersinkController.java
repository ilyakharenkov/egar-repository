package com.example.finalproject.controller.instrument;

import com.example.finalproject.domain.entity.client.UserSecurity;
import com.example.finalproject.domain.entity.instrument.Countersink;
import com.example.finalproject.domain.entity.price.Price;
import com.example.finalproject.service.client.UserSecurityService;
import com.example.finalproject.service.exception.ExceptionService;
import com.example.finalproject.service.image.ImageService;
import com.example.finalproject.service.instrument.CountersinkService;
import jakarta.validation.ConstraintViolationException;
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
    private final ExceptionService exceptionService;
    private final ImageService imageService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/countersink")
    public String findAllCountersink(Model model, Principal principal) {
        model.addAttribute("countersinks", countersinkService.findAll());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "countersink";
    }

    @GetMapping("/countersink/{id}")
    public String getCountersinkDetails(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("countersink", countersinkService.findById(id));
        model.addAttribute("listImage", countersinkService.findById(id).getImageList());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "countersink-details";
    }

    @PostMapping("/countersink/add")
    public String saveCountersink(@RequestParam("listFile") List<MultipartFile> multipartFileList,
                                  Countersink countersink,
                                  Price price) {
        try {
            countersink.setCheckStatus(true);
            countersink.setPrice(price);
            imageService.saveImageCountersink(multipartFileList, countersink);
            countersinkService.save(countersink);
        } catch (ConstraintViolationException e) {
            exceptionService.methodValidationInstrumentException(countersink);
        }
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
