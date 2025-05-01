package com.kola.management.event.event.services;

import com.kola.management.event.event.dto.eventhistory.EventHistoryDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryStartDateDto;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EventHistoryServiceIntegrationTest {

    @Autowired
    IEventHistoryService eventHistoryService;

    @Test
    void givenValidEventHistory_whenCreate_thenSuccess() throws  EventHistoryServiceException {
        EventHistoryDto eventHistoryDto = new EventHistoryDto(
                111111L,
                "eventName",
                "eventVenue",
                "eventDescription",
                1522222L,
                "sdfsfsdfsdf"
        );

        EventHistory created = eventHistoryService.createEventHistory(eventHistoryDto).get();

        assertThat(created.getId()).isNotNull();
        assertThat(created.getEventName()).isEqualTo("eventName");
    }

    @Test
    void givenExistingEventHistory_whenUpdate_thenUpdatedSuccessfully() throws  EventHistoryServiceException {
        EventHistoryDto eventHistoryDto = new EventHistoryDto(
                111111L,
                "eventName",
                "eventVenue",
                "eventDescription",
                1522222L,
                "sdfsfsdfsdf"
        );

        EventHistory created = eventHistoryService.createEventHistory(eventHistoryDto).get();

        EventHistoryStartDateDto eventHistoryStartDateDto =
                new EventHistoryStartDateDto(
                        created.getEventId(),
                        LocalDateTime.now()
                );
        EventHistory updated = eventHistoryService.setEventStartDate(eventHistoryStartDateDto).get();

        assertThat(updated.getEventStartDate()).isEqualTo(eventHistoryStartDateDto.eventStartDate());
        assertThat(updated.getUpdatedAt()).isNotNull();
    }

//    @Test
//    void givenMissingNom_whenCreate_thenThrowsException() {
//        Role role = new Role();
//        role.setDescription("Sans nom");
//        role.setPermissions("LIMITED");
//
//        assertThrows(RoleServiceException.class, () -> {
//            roleService.createRole(role);
//        });
//    }
//
//    @Test
//    void givenMissingPermissions_whenCreate_thenThrowsException() {
//        Role role = new Role();
//        role.setNom("NO_ACCESS");
//        role.setDescription("Rôle fantôme");
//
//        assertThrows(RoleServiceException.class, () -> {
//            roleService.createRole(role);
//        });
//    }
//
//    @Test
//    void givenValidRole_whenFindByNom_thenReturnCorrectResult() throws RoleServiceException {
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
//    }
}
