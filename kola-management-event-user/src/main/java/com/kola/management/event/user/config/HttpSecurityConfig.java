package com.kola.management.event.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;


    public HttpSecurityConfig(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(
                                "/",
                                "/home",
                                "/admin/register",
                                "/admin",
                                "/register",
                                "/resources/**",
                                "/resources/static/**",
                                "/js/**",
                                "/css/**",
                                "/img/**"
                        )
                                .permitAll()

                        //.requestMatchers("/requests/**").permitAll()
                       // .requestMatchers("/**").authenticated()// Allow access to the login page
                        .anyRequest().authenticated()
                        // Secure all other endpoints
                )
                .formLogin(form -> form
                        .loginPage("/login") // Use the custom login page
                        .defaultSuccessUrl("/dashboard", true) // Redirect to welcome page after login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login") // Redirect to login page after logout
                        .permitAll()
                )
                .rememberMe(
                        rememberMe -> rememberMe.rememberMeParameter("remember-me-new")
                )
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
                .authenticationManager(customAuthenticationManager)
                .addFilterBefore(new UserFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }



}

