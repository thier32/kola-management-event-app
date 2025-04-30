package com.kola.management.event.event.services.event.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.repository.EventRepository;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.event.exceptions.EventServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService extends BaseKernelService<Event> implements IEventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) throws EventServiceException {
        Event event1 ;
        try{
           event1 =  this.save(event);
        }catch (KernelException kernelException){
            throw new EventServiceException(kernelException.getMessage());
        }

        return event1;
    }

    @Override
    public Event saveEventDto(IEventDto eventDto) throws EventServiceException {
        Event event = null;
        try {
            event = this.mapping(eventDto, Event.class);
            event = saveEvent(event);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return event;
    }

    @Override
    public Optional<Event> createEvent(EventDto eventDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventUpdateDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventUpdateStateDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventUpdateNameDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventUpdateDescritpinDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException {
        Event event;
        try {
            event = saveEventDto(eventUpdateNameDescriptionDto);
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return  event != null ? Optional.of(event) : Optional.empty();
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
