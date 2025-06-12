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
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/openapi.json"
                        ).permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/menu", "/api/menu/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/reservations", "/api/reservations/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/reservations", "/api/reservations/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/menu", "/api/menu/**").permitAll()

                        .anyRequest().authenticated()
                )

                // JWT‑Filter vor UsernamePasswordAuthenticationFilter einhängen
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
