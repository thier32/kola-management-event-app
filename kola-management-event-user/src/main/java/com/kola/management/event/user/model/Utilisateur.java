package com.kola.management.event.user.model;

import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur extends BaseKernelModel implements UserDetails {

    public static final String utilisateurIdProp = "utilisateurId";
    public static final String nomUtilisateurProp = "nomUtilisateur";
    public static final String emailProp = "email";
    public static final String motDePasseProp = "motDePasse";
    public static final String estActifNameProp = "estActifName";
    public static final String dateCreationProp = "dateCreation";
    

    private Long utilisateurId;

    @Column(name = "nom_utilisateur", unique = true)
    private String nomUtilisateur;

    private String email;

    private String motDePasse;

    @Transient
    private List<Role> roles;

    @Builder.Default
    private boolean estActif = true;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDate.now();
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
    }

    public void addRole(Role role) {
        role.setRoleId(role.getRoleId());
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        for (int i = 0; i < this.roles.size(); i++) {
            Role r = roles.get(i);
            if (Objects.equals(r.getRoleId(), role.getRoleId())) {
                roles.remove(r);
                break;
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
