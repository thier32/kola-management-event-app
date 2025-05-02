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

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}pass") // {noop} indicates plain text password (for demo purposes)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(
//            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(
                                "/home",
                                "/register",
                                "/resources/**",
                                "/resources/static/**",
                                "/js/**",
                                "/css/**"
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
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/requests/list").permitAll()// Allow access to the login page
//                        .anyRequest().authenticated() // Secure all other endpoints
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Use the custom login page
//                        .defaultSuccessUrl("/welcome", true) // Redirect to welcome page after login
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login") // Redirect to login page after logout
//                        .permitAll()
//                ).authenticationProvider(new
//                        CustomAuthenticationProvider()
//                );


        return http.build();
    }

}

