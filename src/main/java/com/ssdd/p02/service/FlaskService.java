package com.ssdd.p02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
}
