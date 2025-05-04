package com.kola.management.event.event.business.event;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.event.IEventService;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import com.kola.management.event.event.services.eventhistory.impl.EventHistoryService;
import com.kola.management.event.event.services.eventspot.IEventSpotService;
import com.kola.management.event.event.services.eventspot.impl.EventSpotService;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventBusiness implements IEventBusiness {

    @Autowired
    IEventService eventService;

    @Autowired
    IEventHistoryService eventHistoryService;

    @Autowired
    IEventSpotService eventSpotService;

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
        }catch (EventServiceException eventServiceException){
            throw new EventBusinessException(eventServiceException.getMessage());
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

    @Override
    public ListDataDto<EventHistory> getEventHistoryListData() {
        return getEventHistoryListData(1);
    }

    @Override
    public ListDataDto<EventHistory> getEventHistoryListData(int page) {
        ListDataDto<EventHistory> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = page;
        List<String> keys = new ArrayList<>();
        Arrays.stream(EventStatus.values()).forEach(e -> keys.add(e.name()));
        data.keys = keys;
        data.elementPerPage = 5;
        data.listElements = this.eventHistoryService.findAllByOrderByIdDesc(data.currentPage,data.elementPerPage);
        data.total = this.eventHistoryService.findAllEventHistory().size();

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
    public ListDataDto<EventHistory> getEventHistoryListData(Integer pageNo) {
        if (pageNo == null){
            return getEventHistoryListData(1);
        }
        return getEventHistoryListData(pageNo.intValue());
    }

    public ListDataDto<EventReturnDto> getEventListData(int page) {
        ListDataDto<EventReturnDto> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = page;
        data.elementPerPage = 5;
        data.listElements = this.eventService.findEventReturnDtoAllByOrderByIdDesc(data.currentPage,data.elementPerPage);
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
    public ListDataDto<EventReturnDto> getEventListData() {
        return getEventListData(1);
    }

    @Override
    public ListDataDto<EventReturnDto> getAllEventListData() {
        ListDataDto<EventReturnDto> listDataDto = new ListDataDto<>();
        listDataDto.listElements = eventService.findEventReturnDtoAllByOrderByIdDesc();
        return listDataDto;
    }

    @Override
    public ListDataDto<EventReturnDto> getEventListData(Integer page) {
        if (page != null){
            return getEventListData(page.intValue());
        }
        return getEventListData();
    }

    @Override
    public EventReturnDto getEvent(Long eventId) throws EventBusinessException {
        Optional<Event> optionalEvent = eventService.findEventByEventId(new EventEventIdDto(eventId));
        EventReturnDto eventReturnDto = null;
        if (optionalEvent.isPresent()){
            try {
                eventReturnDto = eventService.mapping(optionalEvent.get(),EventReturnDto.class);
            }
            catch (KernelException kernelException)
            {
                throw new EventBusinessException(kernelException.getMessage());
            }
        };
        return eventReturnDto;
    }
}
