package com.kola.management.event.user.services;


import com.kola.management.event.user.model.User;
import com.kola.management.event.user.services.exceptions.UserServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserServiceIntegrationTest {

    @Autowired
    IUserService userService;

    @Test
    void givenValidUser_whenCreate_thenSuccess() throws UserServiceException {
        User user = new User();
        user.setUsername("jean.dupont");
        user.setEmail("jean.dupont@example.com");
        user.setPassword("motdepasse123");
        user.setActive(true);
        user.setCreationDate(LocalDateTime.now());

        User created = userService.createUser(user);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getEmail()).isEqualTo("jean.dupont@example.com");
        assertThat(created.isActive()).isTrue();
    }

    @Test
    void givenUser_whenUpdateEmail_thenUpdated() throws UserServiceException {
        User user = new User();
        user.setUsername("amelie.rose");
        user.setEmail("amelie.old@example.com");
        user.setPassword("securePwd");
        user.setActive(true);

        User created = userService.createUser(user);

        User updated = userService.updateUserEmail("amelie.new@example.com", created.getUserId());

        assertThat(updated.getEmail()).isEqualTo("amelie.new@example.com");
    }

    @Test
    void givenMissingFields_whenCreate_thenThrowException() {
        User user = new User();
        user.setEmail("missing.nom@example.com");

        assertThrows(UserServiceException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void givenUser_whenFindByUsername_thenReturnIt() throws UserServiceException {
        User user = new User();
        user.setUsername("luc.martin");
        user.setEmail("luc.martin@example.com");
        user.setPassword("lucpass");
        user.setActive(true);

        userService.createUser(user);

        User found = userService.findUserByUsername("luc.martin");

        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("luc.martin@example.com");

    }

    @Test
    void givenUtilisateur_whenCheckExist_thenReturnTrue() throws UserServiceException {
        User user = new User();
        user.setUsername("marie.durand");
        user.setEmail("marie.durand@example.com");
        user.setPassword("passMar1e");
        user.setActive(true);

        User created = userService.createUser(user);

        boolean exists = userService.verifyUserExists(created.getUserId());

        assertThat(exists).isTrue();
    }
}
