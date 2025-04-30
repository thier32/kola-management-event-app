package com.kola.management.event.user.services;


import com.kola.management.event.user.model.Utilisateur;
import com.kola.management.event.user.services.exceptions.UtilisateurServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UtilisateurServiceIntegrationTest {

    @Autowired
    IUtilisateurService utilisateurService;

    @Test
    void givenValidUtilisateur_whenCreate_thenSuccess() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur("jean.dupont");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setMotDePasse("motdepasse123");
        utilisateur.setEstActif(true);
        utilisateur.setDateCreation(LocalDate.now());

        Utilisateur created = utilisateurService.createUtilisateur(utilisateur);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getEmail()).isEqualTo("jean.dupont@example.com");
        assertThat(created.isEstActif()).isTrue();
    }

    @Test
    void givenUtilisateur_whenUpdateEmail_thenUpdated() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur("amelie.rose");
        utilisateur.setEmail("amelie.old@example.com");
        utilisateur.setMotDePasse("securePwd");
        utilisateur.setEstActif(true);

        Utilisateur created = utilisateurService.createUtilisateur(utilisateur);

        Utilisateur updated = utilisateurService.updateUtilisateurEmail("amelie.new@example.com", created.getUtilisateurId());

        assertThat(updated.getEmail()).isEqualTo("amelie.new@example.com");
    }

    @Test
    void givenMissingFields_whenCreate_thenThrowException() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("missing.nom@example.com");

        assertThrows(UtilisateurServiceException.class, () -> {
            utilisateurService.createUtilisateur(utilisateur);
        });
    }

    @Test
    void givenUtilisateur_whenFindByNomUtilisateur_thenReturnIt() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur("luc.martin");
        utilisateur.setEmail("luc.martin@example.com");
        utilisateur.setMotDePasse("lucpass");
        utilisateur.setEstActif(true);

        utilisateurService.createUtilisateur(utilisateur);

        Utilisateur found = utilisateurService.findUtilisateurByNomUtilisateur("luc.martin");

        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("luc.martin@example.com");
    }

    @Test
    void givenUtilisateur_whenCheckExist_thenReturnTrue() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur("marie.durand");
        utilisateur.setEmail("marie.durand@example.com");
        utilisateur.setMotDePasse("passMar1e");
        utilisateur.setEstActif(true);

        Utilisateur created = utilisateurService.createUtilisateur(utilisateur);

        boolean exists = utilisateurService.verifyUtilisateurExists(created.getUtilisateurId());

        assertThat(exists).isTrue();
    }
}
