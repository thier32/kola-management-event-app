package com.kola.management.event.event.business;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.EventUpdateNameDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EventBusinessIntegrationTest {

    @Autowired
    IEventBusiness eventBusiness;

    @Test
    void givenValidEvent_whenCreate_thenSuccess() throws  EventBusinessException {

        EventDto eventDto = new EventDto(
                "eventName",
                "description",
                "venue",null
        );
        EventReturnDto created = eventBusiness.createEvent(eventDto);

        assertThat(created.getEventId()).isNotNull();
        assertThat(created.getEventName()).isEqualTo("eventName");
    }

}
