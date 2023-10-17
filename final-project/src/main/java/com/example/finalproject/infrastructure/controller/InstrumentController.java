package com.example.finalproject.infrastructure.controller;


import com.example.finalproject.service.InstrumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstrumentController {

    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService){
        this.instrumentService = instrumentService;
    }

    @GetMapping("/")
    public String getInstrument(Model model){
        model.addAttribute("instruments", instrumentService.getInstrument());
        return "instrument";
    }
}
