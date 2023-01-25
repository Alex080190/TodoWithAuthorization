package com.example.todowithauthorization.controllers;

import com.example.todowithauthorization.models.User;
import com.example.todowithauthorization.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user")User user){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String doRegistration(@ModelAttribute("user") @Valid User user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.register(user);
        return "redirect:/auth/login";
    }
}