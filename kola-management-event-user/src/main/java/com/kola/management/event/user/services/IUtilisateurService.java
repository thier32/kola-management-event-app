package com.kola.management.event.user.services;


import com.kola.management.event.user.dto.utilisateur.IUtilisateurDto;
import com.kola.management.event.user.model.Utilisateur;
import com.kola.management.event.user.services.exceptions.UtilisateurServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface IUtilisateurService extends UserDetailsService {

    Utilisateur createUtilisateur(Utilisateur utilisateur) throws UtilisateurServiceException;

    Utilisateur updateUtilisateur(Utilisateur utilisateur, Long utilisateurId) throws UtilisateurServiceException;

    Utilisateur updateUtilisateur(IUtilisateurDto utilisateurDto, Long utilisateurId) throws UtilisateurServiceException;

    Utilisateur updateUtilisateurNomUtilisateur(String nomUtilisateur, Long utilisateurId) throws UtilisateurServiceException;

    Utilisateur updateUtilisateurEmail(String email, Long utilisateurId) throws UtilisateurServiceException;

    Utilisateur updateUtilisateurMotDePasse(String motDePasse, Long utilisateurId) throws UtilisateurServiceException;

    Utilisateur updateUtilisateurEtat(boolean estActif, Long utilisateurId) throws UtilisateurServiceException;

    boolean verifyUtilisateurExists(Long utilisateurId);

    boolean verifyUtilisateurExists(Utilisateur utilisateur);

    boolean verifyUtilisateurExists(String nomUtilisateur);

    Utilisateur findUtilisateurById(Long utilisateurId);

    Utilisateur findUtilisateurByNomUtilisateur(String nomUtilisateur);

    List<Utilisateur> findUtilisateurByEmail(String email);

    List<Utilisateur> findUtilisateurByDateCreation(LocalDate dateCreation);

    // List<Utilisateur> findUtilisateurByRole(String role);

    /***
     * Verify whether a Utilisateur has the provided UtilisateurId
     *
     * @param utilisateurId
     * @return Utilisateur
     * @throws UtilisateurServiceException
     */
    public Utilisateur checkUtilisateurExists(long utilisateurId) throws UtilisateurServiceException;

    /**
     * Verify whether the mandatory params are provided
     *
     * @param utilisateur
     * @throws UtilisateurServiceException
     */
    void checkMandatoryProperty(Utilisateur utilisateur) throws UtilisateurServiceException;
}
