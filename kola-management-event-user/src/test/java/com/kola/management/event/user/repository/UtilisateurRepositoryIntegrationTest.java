package com.kola.management.event.user.repository;

import com.kola.management.event.user.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UtilisateurRepositoryIntegrationTest {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenUtilisateurModel_whenSave_thenSuccess() {
        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur("jdupont");
        user.setEmail("j.dupont@email.com");
        user.setDateCreation(LocalDate.now());

        Utilisateur saved = utilisateurRepository.save(user);
        assertThat(entityManager.find(Utilisateur.class, saved.getId())).isEqualTo(user);
    }

    @Test
    void givenUtilisateurCreated_whenUpdate_thenSuccess() {
        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur("jdoe");
        entityManager.persist(user);

        user.setEmail("john.doe@email.com");
        utilisateurRepository.save(user);

        assertThat(entityManager.find(Utilisateur.class, user.getId()).getEmail()).isEqualTo("john.doe@email.com");
    }

    @Test
    void givenUtilisateurCreated_whenFindById_thenSuccess() {
        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur("mdupuis");
        entityManager.persist(user);

        Utilisateur found = utilisateurRepository.findById(user.getId()).get();
        assertThat(found).isEqualTo(user);
    }
}
