package com.kola.management.event.event.services.event.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.repository.EventRepository;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService extends BaseKernelService<Event> implements IEventService {


    @Override
    public Event saveEventDto(IEventDto eventDto, Long eventId) throws EventServiceException {
        Event event;
        try {
            event = this.mapping(eventDto, Event.class);
            if (eventId == null){
                event = this.save(event);
            }else{
                event = this.update(event, eventId);
            }
        } catch (KernelException e) {
            throw new EventServiceException(e.getMessage());
        }
        return event;
    }

    @Override
    public Optional<Event> updateEvent(IEventDto eventDto, long eventId) throws EventServiceException {
        Event event = this.saveEventDto(eventDto,eventId);
        return event != null ? Optional.of(event) : Optional.empty();
    }

    @Override
    public  Optional<Event> saveEvent(IEventDto eventDto) throws EventServiceException {
        Event event = this.saveEventDto(eventDto);
        return event != null ? Optional.of(event) : Optional.empty();
    }


    @Override
    public Event saveEventDto(IEventDto eventDto) throws EventServiceException {
        return this.saveEventDto(eventDto,null);
    }


    @Override
    public Optional<Event> createEvent(EventDto eventDto) throws EventServiceException {
        return this.saveEvent(eventDto);
    }

    @Override
    public Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException {
        return this.updateEvent(eventUpdateDto,eventUpdateDto.eventId());
    }

    @Override
    public Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException {
        return this.updateEvent(eventUpdateStateDto,eventUpdateStateDto.eventId());
    }

    @Override
    public Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException {
        return this.updateEvent(eventUpdateNameDto,eventUpdateNameDto.eventId());
    }

    @Override
    public Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException {
        return this.updateEvent(eventUpdateDescritpinDto,eventUpdateDescritpinDto.eventId());
    }

    @Override
    public Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException {
        return this.updateEvent(eventUpdateNameDescriptionDto,eventUpdateNameDescriptionDto.eventId());
    }

    @Override
    public Optional<Event> findEventByEventId(EventEventIdDto eventEventIdDto) {
        return ((EventRepository)getDefaultRepository()).findByEventId(eventEventIdDto.eventId());
    }

    @Override
    public List<Event> findEventByEventName(EventNameDto eventNameDto) {
        return ((EventRepository)getDefaultRepository()).findByEventName(eventNameDto.eventName());
    }

    @Override
    public List<Event> findAllEvents() {
        return getDefaultRepository().findAll();
    }
    

    @Override
    public List<Event> findAllByOrderByIdDesc(int page, int element){
        Pageable pageable = Pageable.ofSize( element).withPage( page-1);
        return ((EventRepository)getDefaultRepository()).findByOrderByIdDesc(pageable);
    }
}
