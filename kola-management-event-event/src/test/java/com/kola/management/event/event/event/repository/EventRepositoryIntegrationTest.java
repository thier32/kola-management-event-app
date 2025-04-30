package com.kola.management.event.event.event.repository;

import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.repository.EventRepository;
import com.kola.management.event.user.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EventRepositoryIntegrationTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenRoleModel_whenSave_thenSuccess() {
        Event event = new Event();
        event.setEventName("EventName");

        Event saved = eventRepository.save(event);
        assertThat(entityManager.find(Event.class, saved.getId())).isEqualTo(saved);
    }

    @Test
    void givenRoleCreated_whenUpdate_thenSuccess() {
        Event event = new Event();
        event.setEventName("USER");
        entityManager.persist(event);

        event.setEventName("MANAGER");
        eventRepository.save(event);

        assertThat(entityManager.find(Event.class, event.getId()).getEventName()).isEqualTo("MANAGER");
    }

    @Test
    void givenRoleCreated_whenFindById_thenSuccess() {
        Event event = new Event();
        event.setEventName("SUPPORT");
        entityManager.persist(event);

        Event found = eventRepository.findById(event.getId()).get();
        assertThat(found).isEqualTo(event);
    }
}
