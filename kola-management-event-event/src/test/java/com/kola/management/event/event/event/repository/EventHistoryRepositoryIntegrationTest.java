package com.kola.management.event.event.event.repository;

import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.repository.EventHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EventHistoryRepositoryIntegrationTest {

    @Autowired
    EventHistoryRepository eventHistoryRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenEventHistory_whenSave_thenSuccess() {
        EventHistory eventHistory = new EventHistory();
        EventHistory savedEventHistory = eventHistoryRepository.save(eventHistory);
        assertThat(entityManager.find(EventHistory.class, savedEventHistory.getId())).isEqualTo(eventHistory);
    }

    @Test
    void givenEventHisotryCreated_whenUpdate_thenSuccess() {
        EventHistory eventHistory = new EventHistory();
        entityManager.persist(eventHistory);

        eventHistory.setEventBookerName("booker1");
        EventHistory eventHistory1 = eventHistoryRepository.save(eventHistory);

        assertThat(entityManager.find(EventHistory.class, eventHistory1.getId()).getEventBookerName()).isEqualTo("booker1");
    }

    @Test
    void givenEventHistoryCreated_whenFindById_thenSuccess() {
        EventHistory eventHistory = new EventHistory();
        entityManager.persist(eventHistory);

        EventHistory found = eventHistoryRepository.findById(eventHistory.getId()).get();
        assertThat(found).isEqualTo(eventHistory);
    }
}
