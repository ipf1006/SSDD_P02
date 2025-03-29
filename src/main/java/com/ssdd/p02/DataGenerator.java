package com.ssdd.p02;

import com.ssdd.p02.model.Usuario;
import com.ssdd.p02.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataGenerator {

    @Bean
    public CommandLineRunner cargarUsuariosIniciales(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                usuarioRepository.save(new Usuario("Ignacio", "ipf1006@alu.ubu.es"));
                usuarioRepository.save(new Usuario("Alumno2", "alumno2@alu.ubu.es"));
                usuarioRepository.save(new Usuario("Alumno3", "alumno3@alu.ubu.es"));
                System.out.println("Usuarios insertados correctamente.");
            } else {
                System.out.println("Los usuarios ya existen.");
            }
        };
    }
}

