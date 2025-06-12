package ch.noseryoung.backend.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Wir verwenden JWT, daher kein CSRF-Token nötig
                .csrf(csrf -> csrf.disable())

                // Stateless: jede Anfrage enthält ein eigenes JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Zugriffsregeln definieren
                .authorizeHttpRequests(auth -> auth
                        // Auth‑Endpunkte immer frei
                        .requestMatchers("/api/auth/**").permitAll()
                        // Swagger + OpenAPI frei zugänglich, damit UI /openapi.json laden kann
                        .requestMatchers(
                                "/swagger-ui.html",          // alter Pfad
                                "/swagger-ui/**",            // statische Assets
                                "/v3/api-docs/**",           // JSON/YAML Docs
                                "/openapi.json"              // springdoc.custom-path falls gesetzt
                        ).permitAll()
                        // Menu und Reservations GET-Anfragen ohne Token erlauben
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/menu", "/api/menu/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/reservations", "/api/reservations/**").permitAll()
                        // Alles andere braucht Authentifizierung
                        .anyRequest().authenticated()
                )

                // JWT‑Filter vor UsernamePasswordAuthenticationFilter einhängen
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
