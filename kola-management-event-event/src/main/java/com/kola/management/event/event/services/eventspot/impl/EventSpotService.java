package com.kola.management.event.event.services.eventspot.impl;

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
    String NO_MORE_SPACE_IN_SPOT_TEMPLATE = "The spot %s is fully booked.";
    String INVALID_SPOT_CAPACITY_TEMPLATE = "The provider capacity %s is invalid.";


    @Override
    public Optional<EventSpot> updateEventSpot(IEventSpotDto eventSpotDto, long eventSpotId) throws EventSpotServiceException {

        EventSpot eventSpot = this.saveEventSpotDto(eventSpotDto,eventSpotId);
        return eventSpot != null ? Optional.of(eventSpot) : Optional.empty();
    }



    @Override
    public EventSpot saveEventSpotDto(IEventSpotDto eventSpotDto, Long eventSpotId) throws EventSpotServiceException {
        EventSpot eventSpot;
        try {

            if (eventSpotId == null){
                eventSpot = this.mapping(eventSpotDto, EventSpot.class);
                eventSpot = this.save(eventSpot);
            }else{
                eventSpot = this.update(eventSpotDto, eventSpotId);
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
        if (eventSpot.eventSpotCapacity() < 0L){
            throw new EventSpotServiceException(String.format(
                    INVALID_SPOT_CAPACITY_TEMPLATE,eventSpot.eventSpotCapacity()
            ));
        }

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
    public Optional<EventSpot> updateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws EventSpotServiceException {
        if (eventSpotUpdateStatusDto.eventSpotOccupation() != null){
            checkEventSpotOccupation(eventSpotUpdateStatusDto.eventSpotId(),eventSpotUpdateStatusDto.eventSpotOccupation());
        }
        return this.updateEventSpot(eventSpotUpdateStatusDto,eventSpotUpdateStatusDto.eventSpotId());
    }

    @Override
    public void checkEventSpotOccupation(long evenSpotId, Long occupation) throws EventSpotServiceException {
        EventSpot eventSpot = verifyEventSpotExistByEventSpotId(new EventSpotEventSpotIdDto(evenSpotId));
        Long current = eventSpot.getEventSpotCapacity();
        //if (occupation > 0){
        current -= occupation;
        //}else{
        //    current += occupation;
        //}
        if (current < 0){
            throw new EventSpotServiceException(String.format(NO_MORE_SPACE_IN_SPOT_TEMPLATE,eventSpot.getEventSpotName()));
        }
    }


    @Override
    public Optional<EventSpot> findEventSpotByEventSpotId(EventSpotEventSpotIdDto eventSpotEventSpotIdDto) {
        return ((EventSpotRepository)getDefaultRepository()).findByEventSpotId(
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
        return ((EventSpotRepository)getDefaultRepository()).findByEventSpotId(eventEventIdDto.eventId());
    }


    @Override
    public Optional<EventSpot> verifyEventSpotExistByEventId(EventSpotEventSpotIdEventIdDto eventSpotIdEventIdDto) throws EventServiceException, EventSpotServiceException {
        return ((EventSpotRepository)getDefaultRepository()).
                findByEventSpotId(eventSpotIdEventIdDto.eventId());
    }

    @Override
    public EventSpotReturnDto mapping(BaseKernelModel model, Class<EventSpotReturnDto> eventSpotReturnDtoClass) throws EventSpotServiceException {
        try{
            return super.mapping(model,eventSpotReturnDtoClass);
        }catch (KernelException kernelException){
            throw new EventSpotServiceException(kernelException.getMessage());
        }

    }

    @Override
    public List<EventSpotReturnDto> findEventSpotReturnDtoAllByOrderByIdDesc(int currentPage, int elementPerPage) {
        Pageable pageable = Pageable.ofSize( elementPerPage).withPage( currentPage-1);
        return  ((EventSpotRepository)getDefaultRepository()).findEventReturnDtoAllByOrderByIdDesc(pageable);
    }

    @Override
    public List<EventSpotReturnDto> findEventSpotReturnDtoAllByEventIdOrderByIdDesc(long eventId, int currentPage, int elementPerPage) {
        Pageable pageable = Pageable.ofSize( elementPerPage).withPage( currentPage-1);
        return  ((EventSpotRepository)getDefaultRepository()).findEventReturnDtoAllByEventIdOrderByIdDesc(eventId,pageable);
    }

    @Override
    public List<EventSpotReturnDto> findEventSpotReturnDtoAllByEventIdOrderByIdDesc(long eventId) {
        return  ((EventSpotRepository)getDefaultRepository()).findEventReturnDtoAllByEventIdOrderByIdDesc(eventId);
    }
}
