package com.ssdd.p02.controller;

import com.ssdd.p02.service.FlaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final FlaskService flaskService;

    @Autowired
    public MainController(FlaskService flaskService) {
        this.flaskService = flaskService;
    }

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

    // Simulación de error al acceder a API externa (ej: PokéAPI)
    @GetMapping("/api/pokemon")
    public String errorPokemon(Model model) {
        String resultado = flaskService.getPokemonError();
        model.addAttribute("resultado", resultado);
        return "api-invocacion";
    }
}