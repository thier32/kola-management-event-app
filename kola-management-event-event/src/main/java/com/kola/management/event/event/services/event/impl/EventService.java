package com.kola.management.event.event.services.event.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.event.exceptions.EventServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService extends BaseKernelService<Event> implements IEventService {


    @Override
    public  Optional<Event> saveEvent(IEventDto eventDto) throws EventServiceException {
        Event event = this.saveEventDto(eventDto);
        return event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Event saveEventDto(IEventDto eventDto) throws EventServiceException {
        Event event = null;
        try {
            event = this.mapping(eventDto, Event.class);
            event = this.save(event);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return event;
    }


    @Override
    public Optional<Event> createEvent(EventDto eventDto) throws EventServiceException {
        return this.saveEvent(eventDto);
    }

    @Override
    public Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException {
        return this.saveEvent(eventUpdateDto);
    }

    @Override
    public Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException {
        return  this.saveEvent(eventUpdateStateDto);
    }

    @Override
    public Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException {
        return  this.saveEvent(eventUpdateNameDto);
    }

    @Override
    public Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException {
        return  this.saveEvent(eventUpdateDescritpinDto);
    }

    @Override
    public Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException {
        return  this.saveEvent(eventUpdateNameDescriptionDto);
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
