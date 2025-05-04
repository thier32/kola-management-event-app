package com.kola.management.event.event.business.eventspot;

import com.kola.management.event.event.business.IEventSpotBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.business.exceptions.EventSpotBusinessException;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.eventspot.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventSpot;
import com.kola.management.event.event.services.eventspot.IEventSpotService;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.exceptions.EventSpotServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventSpotBusiness implements IEventSpotBusiness {

    @Autowired
    IEventSpotService eventSpotService;

    @Override
    public EventSpotReturnDto createEventSpot(EventSpotDto eventSpotDto) throws EventSpotBusinessException {
        EventSpotReturnDto eventSpotReturnDto = null;
        try{
            Optional<EventSpot> optionalEventSpot;
            if (eventSpotDto.eventSpotId() != null){
                optionalEventSpot = eventSpotService.updateEventSpot(eventSpotDto,eventSpotDto.eventSpotId());
            }else{
                optionalEventSpot = eventSpotService.createEventSpot(eventSpotDto);
            }

            if (optionalEventSpot.isPresent()){
                eventSpotReturnDto = map(optionalEventSpot.get());
            };
        }catch (EventSpotServiceException eventSpotServiceException){
            throw new EventSpotBusinessException(eventSpotServiceException.getMessage());
        }
        return eventSpotReturnDto;
    }

    @Override
    public EventSpotReturnDto addEventSpotToEvent(EventSpotDto eventSpotDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto removeEventSpotToEvent(EventSpotDto eventSpotDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto publishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto unpublishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto bookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto unbookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpot(EventSpotUpdateDto eventSpotUpdateDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotName(EventSpotUpdateNameDto eventSpotUpdateNameDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotCapacity(EventSpotUpdateCapacityDto eventSpotUpdateCapacityDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotOccupation(EventSpotUpdateOccupationDto eventSpotUpdateOccupationDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotState(EventSpotUpdateStateDto eventSpotUpdateStateDto) {
        return null;
    }

    @Override
    public  EventSpotReturnDto map(BaseKernelModel model) throws EventSpotBusinessException {
        EventSpotReturnDto eventSpotReturnDto;
        try {
            eventSpotReturnDto =
                    eventSpotService.mapping(model, EventSpotReturnDto.class);
        }catch (EventSpotServiceException eventSpotServiceException){
            throw new EventSpotBusinessException(eventSpotServiceException.getMessage());
        }
        return eventSpotReturnDto;
    }
}
