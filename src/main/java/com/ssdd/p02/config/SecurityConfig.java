package com.ssdd.p02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad de la aplicación.
 * Define qué rutas son públicas, la página de login y logout,
 * y el cifrado de contraseñas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Definimos permisos de acceso
                .authorizeHttpRequests(auth -> auth
                        // Permitimos todos a página de inicio, login y recursos estáticos
                        .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                        // El resto requiere autenticación
                        .anyRequest().authenticated()
                )
                // Configuramos el formulario de login
                .formLogin(form -> form
                        .loginPage("/login")           // URL de la vista de login
                        .defaultSuccessUrl("/?login", true)  // A dónde ir tras login correcto
                        .permitAll()
                )
                // Configuramos logout
                .logout(logout -> logout
                        .logoutUrl("/logout")              // URL para hacer logout
                        .logoutSuccessUrl("/login?logout") // Redirigir tras logout
                        .permitAll()
                )
        ;
        return http.build();
    }

    /**
     * Bean que Spring usará para encriptar y comprobar las contraseñas con BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}