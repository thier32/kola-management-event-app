package com.kola.management.event.user.repository;

import com.kola.management.event.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenUtilisateurModel_whenSave_thenSuccess() {
        User user = new User();
        user.setUsername("jdupont");
        user.setEmail("j.dupont@email.com");
        user.setCreationDate(LocalDateTime.now());

        User saved = userRepository.save(user);
        assertThat(entityManager.find(User.class, saved.getId())).isEqualTo(user);
    }

    @Test
    void givenUtilisateurCreated_whenUpdate_thenSuccess() {
        User user = new User();
        user.setUsername("jdoe");
        entityManager.persist(user);

        user.setEmail("john.doe@email.com");
        userRepository.save(user);

        assertThat(entityManager.find(User.class, user.getId()).getEmail()).isEqualTo("john.doe@email.com");
    }

    @Test
    void givenUtilisateurCreated_whenFindById_thenSuccess() {
        User user = new User();
        user.setUsername("mdupuis");
        entityManager.persist(user);

        User found = userRepository.findById(user.getId()).get();
        assertThat(found).isEqualTo(user);
    }
}
