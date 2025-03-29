package com.ssdd.p02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Página principal
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Sistemas Distribuidos - Práctica 02");
        return "index";
    }

    // Pantalla de login
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    // Pantalla de pruebas API Flask
    @GetMapping("/api")
    public String api(Model model) {
        return "api-invocacion";
    }
}