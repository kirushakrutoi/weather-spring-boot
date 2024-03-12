package ru.kirill.WeatherSpringBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kirill.WeatherSpringBoot.models.User;
import ru.kirill.WeatherSpringBoot.services.RegistrationService;
import ru.kirill.WeatherSpringBoot.validations.UserValidation;

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
