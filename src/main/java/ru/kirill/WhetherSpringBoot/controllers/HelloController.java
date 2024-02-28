package ru.kirill.WhetherSpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String getHelloPage(){
        return "hello";
    }
}
