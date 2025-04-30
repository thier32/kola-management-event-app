package com.kola.management.event.user.services.impl;


import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import com.kola.management.event.user.dto.utilisateur.*;
import com.kola.management.event.user.model.Utilisateur;
import com.kola.management.event.user.repository.UtilisateurRepository;
import com.kola.management.event.user.services.IUtilisateurService;
import com.kola.management.event.user.services.exceptions.UtilisateurServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtilisateurService extends BaseKernelService<Utilisateur> implements IUtilisateurService {

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) throws UtilisateurServiceException {
        checkMandatoryProperty(utilisateur);
        verifyUtilisateurExists(utilisateur);
        try {
            utilisateur =  this.save(utilisateur);
        }catch (KernelException exception){
            throw new UtilisateurServiceException(exception.getMessage());
        }
        return utilisateur;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long utilisateurId) throws UtilisateurServiceException {
        try {
            utilisateur = update(utilisateur, utilisateurId);
        } catch (KernelException kernelException) {
            throw new UtilisateurServiceException(kernelException.getMessage());
        }
        return utilisateur;
    }

    @Override
    public Utilisateur updateUtilisateur(IUtilisateurDto utilisateurDto, Long utilisateurId) throws UtilisateurServiceException {
        Utilisateur utilisateur;
        try {
            utilisateur = update(utilisateurDto, utilisateurId);
        } catch (KernelException kernelException) {
            throw new UtilisateurServiceException(kernelException.getMessage());
        }
        return utilisateur;
    }

    @Override
    public Utilisateur updateUtilisateurNomUtilisateur(String nomUtilisateur, Long utilisateurId) throws UtilisateurServiceException {
        UtilisateurNomUtilisateurDto dto = new UtilisateurNomUtilisateurDto(nomUtilisateur);
        return updateUtilisateur(dto, utilisateurId);
    }

    @Override
    public Utilisateur updateUtilisateurEmail(String email, Long utilisateurId) throws UtilisateurServiceException {
        UtilisateurEmailDto dto = new UtilisateurEmailDto(email);
        return updateUtilisateur(dto, utilisateurId);
    }

    @Override
    public Utilisateur updateUtilisateurMotDePasse(String motDePasse, Long utilisateurId) throws UtilisateurServiceException {
        UtilisateurMotDePasseDto dto = new UtilisateurMotDePasseDto(motDePasse);
        return updateUtilisateur(dto, utilisateurId);
    }

    @Override
    public Utilisateur updateUtilisateurEtat(boolean estActif, Long utilisateurId) throws UtilisateurServiceException {
        UtilisateurEstActifDto dto = new UtilisateurEstActifDto(estActif);
        return updateUtilisateur(dto, utilisateurId);
    }


    @Override
    public boolean verifyUtilisateurExists(Long utilisateurId) {
        return findUtilisateurById(utilisateurId) != null;
    }

    @Override
    public boolean verifyUtilisateurExists(Utilisateur utilisateur) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByNomUtilisateur(utilisateur.getNomUtilisateur()) != null;
    }

    @Override
    public boolean verifyUtilisateurExists(String nomUtilisateur) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByNomUtilisateur(nomUtilisateur) != null;
    }

    @Override
    public Utilisateur findUtilisateurById(Long utilisateurId) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByUtilisateurId(utilisateurId);
    }

    @Override
    public Utilisateur findUtilisateurByNomUtilisateur(String nomUtilisateur) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByNomUtilisateur(nomUtilisateur);
    }

    @Override
    public List<Utilisateur> findUtilisateurByEmail(String email) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByEmail(email);
    }

    @Override
    public List<Utilisateur> findUtilisateurByDateCreation(LocalDate dateCreation) {
        return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByDateCreation(dateCreation);
    }

    // @Override
    // public List<Utilisateur> findUtilisateurByRole(String role) {
    //     return ((UtilisateurRepository) getDefaultRepository()).findUtilisateurByRole(role);
    // }

    @Override
    public Utilisateur checkUtilisateurExists(long utilisateurId) throws UtilisateurServiceException {
        Utilisateur utilisateur = findUtilisateurById(utilisateurId);
        if (utilisateur == null) {
            throw new UtilisateurServiceException("Utilisateur with ID " + utilisateurId + " not found.");
        }
        return utilisateur;
    }

    @Override
    public void checkMandatoryProperty(Utilisateur utilisateur) throws UtilisateurServiceException {
        if (utilisateur.getNomUtilisateur() == null || utilisateur.getNomUtilisateur().isEmpty()) {
            throw new UtilisateurServiceException("Property 'nomUtilisateur' is mandatory");
        }
        if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
            throw new UtilisateurServiceException("Property 'email' is mandatory");
        }
        if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isEmpty()) {
            throw new UtilisateurServiceException("Property 'motDePasse' is mandatory");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = findUtilisateurByNomUtilisateur(username);
        if (utilisateur == null){
            //Fake user for developement only
            utilisateur = new Utilisateur();
            utilisateur.setNomUtilisateur(username);
            utilisateur.setMotDePasse("password");
            //throw new UsernameNotFoundException(username);
        }
        return utilisateur;
    }
}
