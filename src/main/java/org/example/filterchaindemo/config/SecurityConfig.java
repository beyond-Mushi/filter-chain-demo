package org.example.filterchaindemo.config;

import lombok.RequiredArgsConstructor;
import org.example.filterchaindemo.securityFilter.AuthenticationLoggingFilter;
import org.example.filterchaindemo.securityFilter.RequestValidationFilter;
import org.example.filterchaindemo.securityFilter.StaticKeyAuthenticationFilter;
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
    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity.addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class);
        httpSecurity.addFilterBefore(
                new RequestValidationFilter(), StaticKeyAuthenticationFilter.class)
                        .addFilterAfter(
                                new AuthenticationLoggingFilter(), StaticKeyAuthenticationFilter.class);
        httpSecurity.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(c->c.anyRequest().permitAll());

        return httpSecurity.build();
    }
}
