package com.ssdd.p02.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdd.p02.model.Usuario;
import com.ssdd.p02.service.FlaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    // Pantalla de gestión de excepciones
    @GetMapping("/api")
    public String api(Model model) {
        return "gestion-excepciones";
    }

    // Maneja las peticiones GET a la URL "/usuarios"
    @GetMapping("/api/db/listado-usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = flaskService.getUsuarios();
        // Añade la lista de usuarios al modelo para que esté disponible en la vista Thymeleaf
        // La clave "usuarios" será usada en el HTML para recorrer e imprimir los datos
        model.addAttribute("usuarios", usuarios);
        // Devuelve el nombre de la vista que se renderizará (usuarios.html en /templates/)
        return "usuarios";
    }

    @GetMapping("/api/db/tabla-inexistente")
    public String errorTablaInexistente(Model model) {
        String resultado = flaskService.consultarTablaInexistente();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }

    @GetMapping("/api/db/conexion-fallida")
    public String errorConexionFallida(Model model) {
        String resultado = flaskService.conexionFallida();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }

    @GetMapping("/api/db/valores-duplicados")
    public String errorValoresDuplicados(Model model) {
        String resultado = flaskService.insertarValoresDuplicados();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }

    @GetMapping("/api/db/valores-nulos")
    public String errorValoresNulos(Model model) {
        String resultado = flaskService.insertarValoresNulos();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }

    @GetMapping("/api/externa/recurso-existente")
    public String errorApiExternaRecursoExistente(Model model) {
        String resultado = flaskService.apiExternaRecursoExistente();
        // Procesar el JSON recibido para extraer datos específicos
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(resultado);

            model.addAttribute("nombre", rootNode.get("nombre").asText());
            model.addAttribute("capital", rootNode.get("capital").asText());
            model.addAttribute("bandera", rootNode.get("bandera").asText());
        } catch (Exception e) {
            model.addAttribute("resultado", "Error al procesar el JSON recibido");
        }
        return "api-externa-recurso-existente";
    }

    @GetMapping("/api/externa/recurso-inexistente")
    public String errorApiExternaRecursoInexistente(Model model) {
        String resultado = flaskService.apiExternaRecursoInexistente();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }

    @GetMapping("/api/externa/solicitud-erronea")
    public String errorApiExternaSolicitudErronea(Model model) {
        String resultado = flaskService.apiExternaSolicitudErronea();
        model.addAttribute("resultado", resultado);
        return "gestion-excepciones";
    }
}