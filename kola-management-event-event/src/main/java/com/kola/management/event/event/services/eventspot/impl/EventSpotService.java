package com.kola.management.event.event.services.eventspot.impl;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.dto.eventspot.*;
import com.kola.management.event.event.model.EventSpot;
import com.kola.management.event.event.repository.EventSpotRepository;
import com.kola.management.event.event.services.eventspot.IEventSpotService;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.exceptions.EventSpotServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventSpotService extends BaseKernelService<EventSpot> implements IEventSpotService {
    String NOT_FOUND_MESSAGE_TEMPLATE = "%s with %s %s not found.";

    @Override
    public Optional<EventSpot> updateEventSpot(IEventSpotDto eventSpotDto, long eventSpotId) throws EventSpotServiceException {
        EventSpot eventSpot = this.saveEventSpotDto(eventSpotDto,eventSpotId);
        return eventSpot != null ? Optional.of(eventSpot) : Optional.empty();
    }

    @Override
    public EventSpot saveEventSpotDto(IEventSpotDto eventSpotDto, Long eventSpotId) throws EventSpotServiceException {
        EventSpot eventSpot;
        try {
            eventSpot = this.mapping(eventSpotDto, EventSpot.class);
            if (eventSpotId == null){
                eventSpot = this.save(eventSpot);
            }else{
                eventSpot = this.update(eventSpot, eventSpotId);
            }
        } catch (KernelException e) {
            throw new EventSpotServiceException(e.getMessage());
        }
        return eventSpot;
    }

    @Override
    public Optional<EventSpot> saveEventSpot(IEventSpotDto EventSpotDto) throws EventSpotServiceException {
        EventSpot eventSpot = this.saveEventSpotDto(EventSpotDto);
        return eventSpot != null ? Optional.of(eventSpot) : Optional.empty();
    }

    @Override
    public EventSpot saveEventSpotDto(IEventSpotDto eventDto) throws EventSpotServiceException {
        return this.saveEventSpotDto(eventDto,null);
    }

    @Override
    public Optional<EventSpot> createEventSpot(EventSpotDto eventSpot) throws EventSpotServiceException {
        return this.saveEventSpot(eventSpot);
    }

    @Override
    public EventSpot saveEventSpotDto(EventSpotDto eventSpotDto) {
        return this.saveEventSpotDto(eventSpotDto);
    }

    @Override
    public Optional<EventSpot> UpdateEventSpot(EventSpotUpdateDto eventUpdateDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventUpdateDto,eventUpdateDto.eventSpotId());
    }

    @Override
    public Optional<EventSpot> UpdateEventSpotState(EventSpotUpdateStateDto eventUpdateStateDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventUpdateStateDto,eventUpdateStateDto.eventSpotId());
    }

    @Override
    public Optional<EventSpot> UpdateEventSpotOccupation(EventSpotUpdateOccupationDto eventSpotUpdateOccupationDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventSpotUpdateOccupationDto,eventSpotUpdateOccupationDto.eventSpotId());
    }

    @Override
    public Optional<EventSpot> UpdateEventSpotName(EventSpotUpdateNameDto eventUpdateNameDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventUpdateNameDto,eventUpdateNameDto.eventSpotId());
    }

    @Override
    public Optional<EventSpot> UpdateEventSpotDescription(EventSpotUpdateDescritpionDto eventUpdateDescritpinDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventUpdateDescritpinDto,eventUpdateDescritpinDto.eventSpotId());
    }

    @Override
    public Optional<EventSpot> UpdateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws EventSpotServiceException {
        return this.updateEventSpot(eventSpotUpdateStatusDto,eventSpotUpdateStatusDto.eventSpotId());
    }


    @Override
    public Optional<EventSpot> findEventSpotByEventSpotId(EventSpotEventSpotIdDto eventSpotEventSpotIdDto) {
        return ((EventSpotRepository)getDefaultRepository()).findEventSpotByEventSpotId(
                eventSpotEventSpotIdDto.eventSpotId()
        );
    }



    @Override
    public EventSpot verifyEventSpotExistByEventSpotId(EventSpotEventSpotIdDto eventSpotEventSpotIdDto) throws EventSpotServiceException {
        Optional<EventSpot> optionalEventSpot = this.findEventSpotByEventSpotId(
                eventSpotEventSpotIdDto
        );
        if (optionalEventSpot.isEmpty()){
            throw new EventSpotServiceException(
                String.format(NOT_FOUND_MESSAGE_TEMPLATE,
                  EventSpot.class.getSimpleName(),
                  EventSpot.eventSpotIdProp,
                        eventSpotEventSpotIdDto.eventSpotId()
                        )
            );
        }
        return optionalEventSpot.get();
    }

    @Override
    public List<EventSpot> findEventByEventName(EventSpotNameDto eventSpotNameDto) {
        return ((EventSpotRepository)getDefaultRepository()).findEventSpotByEventSpotName(eventSpotNameDto.eventSpotName());
    }

    @Override
    public List<EventSpot> findAllEventSpots() {
        return getDefaultRepository().findAll();
    }

    @Override
    public List<EventSpot> findAllByOrderByIdDesc(int page, int element) {
        Pageable pageable = Pageable.ofSize( element).withPage( page-1);
        return ((EventSpotRepository)getDefaultRepository()).findByOrderByIdDesc(pageable);
    }

    @Override
    public Optional<EventSpot> findEventSpotByEventSpotId(EventSpotEventSpotIdEventIdDto eventEventIdDto) {
        return ((EventSpotRepository)getDefaultRepository()).findEventSpotByEventSpotId(eventEventIdDto.eventId());
    }


    @Override
    public Optional<EventSpot> verifyEventSpotExistByEventId(EventSpotEventSpotIdEventIdDto eventSpotIdEventIdDto) throws EventServiceException, EventSpotServiceException {
        return ((EventSpotRepository)getDefaultRepository()).
                findEventSpotByEventSpotId(eventSpotIdEventIdDto.eventId());
    }

    @Override
    public EventSpotReturnDto mapping(BaseKernelModel model, Class<EventSpotReturnDto> eventSpotReturnDtoClass) throws EventSpotServiceException {
        try{
            return super.mapping(model,eventSpotReturnDtoClass);
        }catch (KernelException kernelException){
            throw new EventSpotServiceException(kernelException.getMessage());
        }

    }
}
