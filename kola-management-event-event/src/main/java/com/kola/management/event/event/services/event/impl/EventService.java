package com.kola.management.event.event.services.event.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.event.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {
    @Override
    public Optional<Event> createEvent(EventDto eventDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> findEventByEventId(EventEventIdDto eventEventIdDto) {
        return Optional.empty();
    }

    @Override
    public List<Event> findEventByEventName(EventNameDto eventNameDto) {
        return List.of();
    }
}
