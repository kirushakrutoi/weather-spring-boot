package ru.kirill.WhetherSpringBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.kirill.WhetherSpringBoot.exceptions.UserNotCreatedException;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.services.RegistrationService;
import ru.kirill.WhetherSpringBoot.validations.UserValidation;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final RegistrationService registrationService;
    private final UserValidation userValidation;

    @Autowired
    public AuthenticationController(RegistrationService registrationService, UserValidation userValidation) {
        this.registrationService = registrationService;
        this.userValidation = userValidation;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(@ModelAttribute("user") User user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult){

        userValidation.validate(user, bindingResult);

        if(bindingResult.hasErrors())
            return "auth/registration";

        registrationService.register(user);

        return "redirect:auth/login";
    }
}
