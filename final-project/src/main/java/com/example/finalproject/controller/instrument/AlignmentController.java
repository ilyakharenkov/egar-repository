package com.example.finalproject.controller.instrument;

import com.example.finalproject.domain.dto.instrument.AlignmentDto;
import com.example.finalproject.domain.dto.price.PriceDto;
import com.example.finalproject.domain.entity.instrument.Alignment;
import com.example.finalproject.domain.entity.price.Price;
import com.example.finalproject.service.client.UserSecurityService;
import com.example.finalproject.service.image.ImageService;
import com.example.finalproject.service.instrument.AlignmentService;
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
    private final UserSecurityService userSecurityService;
    private final ImageService imageService;

    @GetMapping("/alignment")
    public String viewListAlignment(Model model, Principal principal){
        model.addAttribute("alignments", alignmentService.findAll());
        model.addAttribute("principal", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "alignment";
    }

    @GetMapping("/alignment/{id}")
    public String alignmentDetails(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("alignment", alignmentService.getAlignmentById(id));
        return "alignment-details";
    }

    @PostMapping("/alignment/add")
    public String addAlignment(@RequestParam("listFile") List<MultipartFile> multipartFileList,
                               AlignmentDto alignmentDto,
                               PriceDto priceDto){
        alignmentService.save(alignmentDto, priceDto);
        imageService.save(multipartFileList);
        return "redirect:/alignment";
    }

    @PostMapping("/alignment/delete/{id}")
    public String deleteAlignment(@PathVariable(name = "id") Long id){
        alignmentService.deleteById(id);
        return "redirect:/alignment";
    }

}
