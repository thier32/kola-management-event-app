package com.kola.management.event.user.config;

import com.kola.management.event.user.services.impl.UtilisateurService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {


    // We are free to implement any authentication logic we want.
    // In our case, we use our existing UserService to load the user data by username, and then we
    // will check if the password, provided in the request, matches the hash of the password from the
    // database
    private final UtilisateurService utilisateurService;


    private final PasswordEncoder passwordEncoder;


    public CustomAuthenticationManager(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        UserDetails userCredentialsByUsername =
                utilisateurService.loadUserByUsername(authentication.getName());


//        if (!passwordEncoder.matches(
//                authentication.getCredentials().toString(), userCredentialsByUsername.getPassword())) {
//            //throw new ApplicationAuthenticationException("Bad credentials");
//        }


//        UserDetails authUser =
//                new AuthUser(
//                        userCredentialsByUsername.userResponse().id(),
//                        userCredentialsByUsername.userResponse().roles(),
//                        userCredentialsByUsername.passwordHash());


        // if null would be returned, then another implementation of authentication provider,
        // that support given type of the authentication will be invoked
        return new UsernamePasswordAuthenticationToken(
                userCredentialsByUsername, authentication.getCredentials(), userCredentialsByUsername.getAuthorities());
    }

}
