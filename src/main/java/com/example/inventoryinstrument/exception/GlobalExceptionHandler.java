package com.example.inventoryinstrument.exception;

import com.example.inventoryinstrument.client.service.UserSecurityService;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final UserSecurityService userSecurityService;

    //Валидация данных при сохранении инструментов.
    @ExceptionHandler(ConstraintViolationException.class)
    @GetMapping("/custom-exception-violation")
    public String methodValidateSaveInstrument(ConstraintViolationException e, Model model, Principal principal) {
        model.addAttribute("exception", e.getConstraintViolations());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "custom-exception-violation";
    }

    //Валидация Renovation (Обслуживание) инструмента.
    @ExceptionHandler(NullPointerException.class)
    @GetMapping("/custom-exception-null-pointer")
    public String methodValidateRenovation(Model model, NullPointerException nullPointerException, Principal principal) {
        model.addAttribute("exception", nullPointerException.getMessage());
        model.addAttribute("client", userSecurityService.findByPrincipal(principal));
        model.addAttribute("role", userSecurityService.findByRoleAdmin(principal));
        return "custom-exception-null-pointer";
    }

}
