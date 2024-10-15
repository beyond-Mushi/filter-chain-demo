package org.example.filterchaindemo.config;

import lombok.RequiredArgsConstructor;
import org.example.filterchaindemo.securityFilter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor

public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.addFilterBefore(
                new RequestValidationFilter(), BasicAuthenticationFilter.class);
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(c->c.anyRequest().permitAll());

        return httpSecurity.build();
    }
}
