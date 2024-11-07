package br.edu.cefsa.macacarefa.config;

import br.edu.cefsa.macacarefa.service.ApeService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    private ApeService service;

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers(mvc.pattern("/login"), mvc.pattern("/cadastro"), mvc.pattern("/index"), mvc.pattern("/home"), mvc.pattern("/grupo"), mvc.pattern("/images/*")).permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                )
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console()).disable()
                )
                .headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // Usa o customizador com defaults para permitir o uso de frames no console H2
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder()); // Adiciona o PasswordEncoder aqui
        return provider;
    }
}
