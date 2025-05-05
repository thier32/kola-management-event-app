package com.kola.management.event.user.repository;

import com.kola.management.event.user.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RoleRepositoryIntegrationTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenRoleModel_whenSave_thenSuccess() {
        Role role = new Role();
        role.setName("ADMIN");

        Role saved = roleRepository.save(role);
        assertThat(entityManager.find(Role.class, saved.getId())).isEqualTo(role);
    }

    @Test
    void givenRoleCreated_whenUpdate_thenSuccess() {
        Role role = new Role();
        role.setName("USER");
        entityManager.persist(role);

        role.setName("MANAGER");
        roleRepository.save(role);

        assertThat(entityManager.find(Role.class, role.getId()).getName()).isEqualTo("MANAGER");
    }

    @Test
    void givenRoleCreated_whenFindById_thenSuccess() {
        Role role = new Role();
        role.setName("SUPPORT");
        entityManager.persist(role);

        Role found = roleRepository.findById(role.getId()).get();
        assertThat(found).isEqualTo(role);
    }
}
