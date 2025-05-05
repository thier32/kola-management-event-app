package com.kola.management.event.user.services;

import com.kola.management.event.user.model.Role;
import com.kola.management.event.user.services.exceptions.RoleServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RoleServiceIntegrationTest {

    @Autowired
    IRoleService roleService;

    @Test
    void givenValidRole_whenCreate_thenSuccess() throws RoleServiceException {
        Role role = new Role();
        role.setName("ADMIN");
        role.setDescription("Administrateur de la plateforme");
        role.setPermissions("ALL");

        Role created = roleService.createRole(role);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("ADMIN");
        assertThat(created.getPermissions()).isEqualTo("ALL");
    }

    @Test
    void givenExistingRole_whenUpdate_thenUpdatedSuccessfully() throws RoleServiceException {
        Role role = new Role();
        role.setName("USER");
        role.setDescription("Utilisateur basique");
        role.setPermissions("READ_ONLY");

        Role created = roleService.createRole(role);
        created.setPermissions("READ_WRITE");

        Role updated = roleService.updateRole(created, created.getRoleId());

        assertThat(updated.getPermissions()).isEqualTo("READ_WRITE");
        assertThat(updated.getUpdatedAt()).isNotNull();
    }

    @Test
    void givenMissingNom_whenCreate_thenThrowsException() {
        Role role = new Role();
        role.setDescription("Sans nom");
        role.setPermissions("LIMITED");

        assertThrows(RoleServiceException.class, () -> {
            roleService.createRole(role);
        });
    }

    @Test
    void givenMissingPermissions_whenCreate_thenThrowsException() {
        Role role = new Role();
        role.setName("NO_ACCESS");
        role.setDescription("Rôle fantôme");

        assertThrows(RoleServiceException.class, () -> {
            roleService.createRole(role);
        });
    }

    @Test
    void givenValidRole_whenFindByNom_thenReturnCorrectResult() throws RoleServiceException {
        Role role = new Role();
        role.setName("GESTIONNAIRE");
        role.setDescription("Responsable des dossiers");
        role.setPermissions("MANAGE_DOSSIERS");

        roleService.createRole(role);

        List<Role> roles = roleService.findRoleByName("GESTIONNAIRE");

        assertThat(roles).isNotEmpty();
        assertThat(roles.get(0).getDescription()).contains("Responsable");
    }
}
