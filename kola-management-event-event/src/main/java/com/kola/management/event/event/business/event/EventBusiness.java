package com.kola.management.event.event.business.event;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.eventhistory.impl.EventHistoryService;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.event.impl.EventService;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventBusiness implements IEventBusiness {

    @Autowired
    EventService eventService;

    @Autowired
    EventHistoryService eventHistoryService;

    @Override
    public EventReturnDto createEvent(EventDto eventDto) throws EventBusinessException {
        EventReturnDto eventReturnDto = null;
        try{
            Optional<Event> optionalEvent;
            if (eventDto.eventId() != null){
                optionalEvent = eventService.updateEvent(eventDto,eventDto.eventId());
            }else{
                optionalEvent = eventService.createEvent(eventDto);
            }

            if (optionalEvent.isPresent()){
                eventReturnDto = map(optionalEvent.get());
            };
        }catch (EventServiceException|EventBusinessException eventServiceException){
            throw new EventBusinessException(eventServiceException.getMessage());
        }

        return eventReturnDto;
    }

    EventReturnDto map(BaseKernelModel model) throws EventBusinessException {
        EventReturnDto eventReturnDto;
        try {
            eventReturnDto =
                    eventService.mapping(model,EventReturnDto.class);
        }catch (KernelException kernelException){
            throw new EventBusinessException(kernelException.getMessage());
        }
        return eventReturnDto;
    }


    @Override
    public EventReturnDto updateEvent(EventUpdateDto eventUpdateDto) {
        return null;
    }

    @Override
    public EventReturnDto updateStartEvent(EventHistoryStartDateDto eventHistoryStartDateDto) {
        return null;
    }

    @Override
    public EventReturnDto updateEndEvent(EventHistoryEndDateDto eventHistoryEndDateDto) {
        return null;
    }

    @Override
    public EventReturnDto publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) {
        return null;
    }

    public EventReturnDto changeEventStatus(long eventId, EventStatus eventStatus) throws EventBusinessException {
        Event event;
        try {
            event = this.eventService.verifyEventExistByEventId(new EventEventIdDto(eventId));
        } catch (EventServiceException e) {
            throw new EventBusinessException(e.getMessage());
        }
        return this.changeEventStatus(event,eventStatus);
    }

    @Override
    public EventReturnDto changeEventStatus(Event event, EventStatus eventStatus) throws EventBusinessException {
        EventReturnDto eventReturnDto = null;

        try {
            EventHistoryChangeStatusEventDto eventHistoryChangeStatusEventDto =
                    new EventHistoryChangeStatusEventDto(
                    event.getEventId(),
                    event.getEventName(),
                    eventStatus
            );
            Optional<EventHistory> optionalEventHistory = this.eventHistoryService.
                    findEventHistoryBychangeStatusEvent(
                      eventHistoryChangeStatusEventDto
                    );

            if (optionalEventHistory.isEmpty()){
                optionalEventHistory = this.eventHistoryService.changeStatusEvent(
                        eventHistoryChangeStatusEventDto
                );
            }

            if (optionalEventHistory.isPresent()){
                eventReturnDto = this.map(optionalEventHistory.get());
            }

        } catch (EventHistoryServiceException e) {
            throw new EventBusinessException(e.getMessage());
        }

        return  eventReturnDto;
    }

    @Override
    public EventReturnDto publishEvent(long eventId) throws EventBusinessException {
        return this.changeEventStatus(eventId,EventStatus.PUBLISHED);
    }

    @Override
    public EventReturnDto unPublishEvent(long eventId) throws EventBusinessException {
        return this.changeEventStatus(eventId,EventStatus.UNPUBLISHED);
    }

    @Override
    public EventReturnDto bookEvent(long eventId) throws EventBusinessException {
        return this.changeEventStatus(eventId,EventStatus.BOOKED);
    }

    @Override
    public EventReturnDto unBookEvent(long eventId) throws EventBusinessException {
        return this.changeEventStatus(eventId,EventStatus.UNBOOKED);
    }

    public ListDataDto<Event> getListData(int page) {
        ListDataDto<Event> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = page;
        data.elementPerPage = 5;
        data.listElements = this.eventService.findAllByOrderByIdDesc(data.currentPage,data.elementPerPage);
        data.total = this.eventService.findAllEvents().size();

        long divisor = data.total;
        if (divisor <= 0){
            divisor = 1;
        }

        long reste = data.elementPerPage % divisor;
        long nbPage =  divisor / data.elementPerPage;
        if (reste != 0){
            nbPage++;
        }
        if (nbPage <= 0){
            nbPage = 1;
        }
        if (data.listElements.isEmpty()) data.listElements = null;
        data.numberPage = (int) nbPage;
        return data;
    }


    @Override
    public ListDataDto<Event> getListData() {
        return getListData(1);
    }

    @Override
    public EventReturnDto getEvent(Long eventId) throws EventBusinessException {
        Optional<Event> optionalEvent = eventService.findEventByEventId(new EventEventIdDto(eventId));
        EventReturnDto eventReturnDto = null;
        if (optionalEvent.isPresent()){
            try {
                eventReturnDto = eventService.mapping(optionalEvent.get(),EventReturnDto.class);
            }catch (KernelException kernelException){
                throw new EventBusinessException(kernelException.getMessage());
            }
        };

        return eventReturnDto;
    }
}
