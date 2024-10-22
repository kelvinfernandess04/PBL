package br.edu.cefsa.macacarefa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/register").permitAll() // Permite login e registro sem autenticação
                .anyRequest().authenticated() // Qualquer outra URL requer login
            )
            .formLogin(form -> form
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/home", true) // Página redirecionada após login bem-sucedido
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redireciona após logout
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar criptografia para senhas
    }
}
