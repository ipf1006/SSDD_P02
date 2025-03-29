package com.ssdd.p02.service;

import com.ssdd.p02.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service // De este modo Spring la gestiona como un bean
public class FlaskService {

    // Inyecta el valor de la propiedad definida en application.properties
    @Value("${api.flask.url}")
    private String flaskUrl;

    // Objeto de Spring para realizar llamadas HTTP (GET, POST...)
    private final RestTemplate restTemplate = new RestTemplate();

    // Método para invocar el endpoint que simula error al acceder a una API de terceros
    public String getPokemonError() {
        return getResponse("/api/pokemon", "Error al acceder a API de terceros");
    }

    /**
     * Método para hacer una petición GET al microservicio Flask
     * @param endpoint Ruta relativa
     * @param errorContext Mensaje base en caso de error
     * @return Respuesta de Flask o mensaje de error procesado
     */
    private String getResponse(String endpoint, String errorContext) {
        try {
            String url = flaskUrl + endpoint;
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return errorContext + ": " + e.getMessage();
        }
    }

    // Método que invoca al microservicio Flask y transforma el JSON recibido en una lista de objetos Usuario
    public List<Usuario> getUsuarios() {
        try {
            String url = flaskUrl + "/api/usuarios";
            Usuario[] usuarios = restTemplate.getForObject(url, Usuario[].class);
            return Arrays.asList(usuarios); // Convertimos el array de usuarios en una lista Java
        } catch (RestClientException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return Collections.emptyList(); // Devolvemos una lista vacía para que la aplicación no falle
        }
    }
}
