package com.kola.management.event.user.config;

import com.kola.management.event.user.services.IUtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserFilter extends OncePerRequestFilter {

    @Autowired
    IUtilisateurService utilisateurService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        UserService userService = new UserService();
        System.out.println("Requête"+request);

        /*final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }*/

        // Get user identity and set it on the spring security context
        //String[] credentials = new String[]{username,password};
       // UserDetails userDetails = userService.loadUserByCredentials(credentials);
        /*UserDetails userDetails = userRepo
                .findByUsername(jwtTokenUtil.getUsername(token))
                .orElse(null);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);*/
//        UserDetails userDetails = utilisateurService.loadUserByCredentials(credentials);
//        UsernamePasswordAuthenticationToken
//                authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null,
//                userDetails == null ?
//                        List.of() : userDetails.getAuthorities()
//        );
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        //this.utilisateurService.loadUserByUsername();
        filterChain.doFilter(request, response);
    }
}
