package org.campusconnect.estudafacil.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter filter;
    private final CustomUserDetailsService securityService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(HttpMethod.POST, "/api/autenticacao").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/pessoa/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/discente/cadastrar/{idCurso}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/papel/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/curso/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/turno/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/situacao-matricula/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/calendario-academico/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/usuario/carregar-usuario-logado").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/alocacao-discente/carregar-alocacao").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/alocacao-discente/carregar-alocacoes").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/alocacao-docente/carregar-alocacoes").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/matricula-curso/carregar-matricula").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/curso/carregar-curso").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/ficha-individual/registrar-notas-frequencias").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/turma/consolidar-turma").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

/*    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}