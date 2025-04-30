package com.kola.management.event.user.repository;


import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.user.model.Utilisateur;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UtilisateurRepository extends BaseKernelRepository<Utilisateur> {

    Utilisateur findUtilisateurByUtilisateurId(Long utilisateurId);

    Utilisateur findUtilisateurByNomUtilisateur(String nomUtilisateur);

    List<Utilisateur> findUtilisateurByEmail(String email);

    List<Utilisateur> findUtilisateurByDateCreation(LocalDate dateCreation);
}
