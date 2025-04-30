package com.kola.management.event.event.event.services;

import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.EventUpdateNameDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.user.model.Role;
import com.kola.management.event.user.services.IRoleService;
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
public class EventServiceIntegrationTest {

    @Autowired
    IEventService eventService;

    @Test
    void givenValidEvent_whenCreate_thenSuccess() throws RoleServiceException {

        EventDto eventDto = new EventDto(
                "eventName",
                "description",
                "venue"
        );
        Event created = eventService.createEvent(eventDto).get();


        assertThat(created.getId()).isNotNull();
        assertThat(created.getEventName()).isEqualTo("eventName");
//        assertThat(created.getPermissions()).isEqualTo("ALL");
    }

    @Test
    void givenExistingEvent_whenUpdate_thenUpdatedSuccessfully() throws RoleServiceException {
        EventDto eventDto = new EventDto(
                "eventName1",
                "description",
                "venue"
        );
        Event event = eventService.createEvent(eventDto).get();


        Event updated = eventService.UpdateEventName(
                new EventUpdateNameDto(
                        "newName",
                        event.getEventId()
                )
        ).get();


        assertThat(updated.getEventName()).isEqualTo("newName");
        assertThat(updated.getUpdatedAt()).isNotNull();
    }

    @Test
    void givenMissingNom_whenCreate_thenThrowsException() {
//        Role role = new Role();
//        role.setDescription("Sans nom");
//        role.setPermissions("LIMITED");
//
//        assertThrows(RoleServiceException.class, () -> {
//            roleService.createRole(role);
//        });
    }

    @Test
    void givenMissingPermissions_whenCreate_thenThrowsException() {
//        Role role = new Role();
//        role.setNom("NO_ACCESS");
//        role.setDescription("Rôle fantôme");
//
//        assertThrows(RoleServiceException.class, () -> {
//            roleService.createRole(role);
//        });
    }

    @Test
    void givenValidRole_whenFindByNom_thenReturnCorrectResult() throws RoleServiceException {
//        Role role = new Role();
//        role.setNom("GESTIONNAIRE");
//        role.setDescription("Responsable des dossiers");
//        role.setPermissions("MANAGE_DOSSIERS");
//
//        roleService.createRole(role);
//
//        List<Role> roles = roleService.findRoleByNom("GESTIONNAIRE");
//
//        assertThat(roles).isNotEmpty();
//        assertThat(roles.get(0).getDescription()).contains("Responsable");
    }
}
