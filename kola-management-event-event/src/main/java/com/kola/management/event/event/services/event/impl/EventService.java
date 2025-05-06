package com.kola.management.event.event.services.event.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.repository.EventRepository;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService extends BaseKernelService<Event> implements IEventService {

    @Value("${kola.event.management.folder.event.images}")
    private String eventFolder;

    @Override
    public Event saveEventDto(IEventDto eventDto, Long eventId) throws EventServiceException {
        Event event;
        try {
            if (eventId == null){
                event = this.mapping(eventDto, Event.class);
                event = this.save(event);
            }else{
                event = this.update(eventDto, eventId);
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
        if (event != null && eventDto instanceof EventDto && ((EventDto)eventDto).eventImage() != null
                && !Objects.requireNonNull(((EventDto) eventDto).eventImage().getOriginalFilename()).isEmpty()
        )
        {

            String imageUrl  = "";
                try {
                  imageUrl = this.uploadImage(((EventDto)eventDto).eventImage(),this.eventFolder, String.valueOf(event.getEventId()));
                } catch (KernelException e) {
                    throw new EventServiceException(e.getMessage());
                }
            return   this.updateEvent(new EventUpdateImageUrlDto(
                        imageUrl,
                        event.getEventId()
                ),event.getEventId());
        }
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
    public Optional<Event> updateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException {
        return this.updateEvent(eventUpdateDto,eventUpdateDto.eventId());
    }

    @Override
    public Optional<Event> updateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException {
        return this.updateEvent(eventUpdateStateDto,eventUpdateStateDto.eventId());
    }

    @Override
    public Optional<Event> updateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException {
        return this.updateEvent(eventUpdateNameDto,eventUpdateNameDto.eventId());
    }

    @Override
    public Optional<Event> updateEventImage(EventUpdateImageUrlDto eventUpdateImageUrlDto) throws EventServiceException {
        return this.updateEvent(eventUpdateImageUrlDto,eventUpdateImageUrlDto.eventId());
    }


    @Override
    public Optional<Event> updateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException {
        return this.updateEvent(eventUpdateDescritpinDto,eventUpdateDescritpinDto.eventId());
    }

    @Override
    public Optional<Event> updateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException {
        return this.updateEvent(eventUpdateNameDescriptionDto,eventUpdateNameDescriptionDto.eventId());
    }

    @Override
    public Optional<Event> findEventByEventId(EventEventIdDto eventEventIdDto) {
        return ((EventRepository)getDefaultRepository()).findByEventId(eventEventIdDto.eventId());
    }

    @Override
    public Event verifyEventExistByEventId(EventEventIdDto eventEventIdDto) throws EventServiceException {
        Optional<Event> optionalEvent = this.findEventByEventId(eventEventIdDto);

        if(optionalEvent.isEmpty()){
            throw  new EventServiceException(
                    String.format(NOT_FOUND_MESSAGE_TEMPLATE,
                    Event.class.getSimpleName(),
                    Event.eventIdProp,
                    eventEventIdDto.eventId()
                    ));
        }

        return optionalEvent.get();
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

    @Override
    public List<EventReturnDto> findEventReturnDtoAllByOrderByIdDesc(int page, int element) {
        Pageable pageable = Pageable.ofSize( element).withPage( page-1);
        return ((EventRepository)getDefaultRepository()).findEventReturnDtoByOrderByIdDesc(pageable);
    }

    @Override
    public List<EventReturnDto> findEventReturnDtoAllByOrderByIdDesc() {
        return ((EventRepository)getDefaultRepository()).findEventReturnDtoByOrderByIdDesc();
    }

    @Override
    public EventReturnDto mapping(BaseKernelModel model, Class<EventReturnDto> eventReturnDtoClass) throws EventServiceException {
        try {
            return super.mapping(model,eventReturnDtoClass);
        }catch (KernelException kernelException){
            throw new EventServiceException(kernelException.getMessage());
        }
    }

    @Override
    public Optional<Event> updateEventOccupation(EventUpdateOccupationDto eventUpdateOccupationDto) throws EventServiceException {
        Optional<Event> optionalEvent = findEventByEventId(new EventEventIdDto(eventUpdateOccupationDto.eventId()));
        if (optionalEvent.isEmpty()){
          throw new EventServiceException(String.format(NOT_FOUND_MESSAGE_TEMPLATE,Event.class.getSimpleName(),Event.eventIdProp,eventUpdateOccupationDto.eventId()));
        }
        Event event = optionalEvent.get();
        Long capacity = event.getEventCapacity();

        if (capacity == null){
            throw new EventServiceException(
                    String.format(NO_SPOT_DEFINED_MESSAGE_TEMPLATE,event.getEventName())
            );
        }

        Long currentOccupation = event.getEventOccupation();
        Long occupation = eventUpdateOccupationDto.eventOccupation();
        if (currentOccupation != null){
            occupation = currentOccupation + eventUpdateOccupationDto.eventOccupation();
        }
        Long diff = capacity - occupation;
        if (diff < 0L || diff > capacity){
            throw new EventServiceException(
                    String.format(NOT_ENOUGH_SPOT_MESSAGE_TEMPLATE,event.getEventName())
            );
        }

        EventUpdateOccupationDto eventUpdateOccupationDtoActual = new EventUpdateOccupationDto(
          event.getEventId(),
          occupation
        );
        return this.updateEvent(eventUpdateOccupationDtoActual,eventUpdateOccupationDto.eventId());
    }
}
