package com.kola.management.event.event.services.eventhistory.impl;

import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.dto.eventspot.EventSpotStatus;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.repository.EventHistoryRepository;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import com.kola.management.event.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventHistoryService extends BaseKernelService<EventHistory> implements IEventHistoryService {

    User getConnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return ((User)authentication.getPrincipal());
        }
        return null;
    }

    @Override
    public EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto,Long eventHistoryId) throws EventHistoryServiceException{
        EventHistory eventHistory;
        try {
            if(eventHistoryId == null){
                eventHistory = this.mapping(eventHistoryDto, EventHistory.class);
                eventHistory = this.save(eventHistory);
            }else{
                eventHistory = this.update(eventHistoryDto,eventHistoryId);
            }
        } catch (KernelException e) {
            throw new EventHistoryServiceException(e.getMessage());
        }
        return eventHistory;
    }

    @Override
    public EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException {
        return saveEventHistoryDto(eventHistoryDto,null);
    }


    @Override
    public Optional<EventHistory> saveEventHistory(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException {
        EventHistory eventHistory =  this.saveEventHistoryDto(eventHistoryDto);
        return eventHistory != null ? Optional.of(eventHistory) : Optional.empty();
    }

    @Override
    public Optional<EventHistory> createEventHistory(EventHistoryDto eventHistoryDto) throws EventHistoryServiceException {
        return  this.saveEventHistory(eventHistoryDto);
    }

    @Override
    public Optional<EventHistory> setEventStartDate(EventHistoryStartDateDto eventHistoryStartDateDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryStartDateDto);
    }

    @Override
    public Optional<EventHistory> setEventEndDate(EventHistoryEndDateDto eventHistoryEndDateDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryEndDateDto);
    }

    @Override
    public Optional<EventHistory> publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryPublisherEventDto);
    }


    @Override
    public Optional<EventHistory> changeStatusEvent(EventHistoryChangeStatusEventDto eventHistoryChangeStatusEventDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryChangeStatusEventDto);
    }

    @Override
    public Optional<EventHistory> findEventHistoryBychangeStatusEvent(EventHistoryChangeStatusEventDto eventHistoryChangeStatusEventDto) throws EventHistoryServiceException {
        return ((EventHistoryRepository)getDefaultRepository()).findFirstByEventIdAndEventStatusOrderByIdDesc(
                eventHistoryChangeStatusEventDto.eventId(),
                eventHistoryChangeStatusEventDto.eventStatus()
        );
    }

    @Override
    public Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) throws EventHistoryServiceException {
        User user = getConnectedUser();
        return this.saveEventHistory(eventHistoryBookerEventDto);
    }

    @Override
    public Optional<EventHistory> bookEventSpot(EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryBookerEventSpotDto);
    }

    @Override
    public Optional<EventHistory> unbookEventSpot(EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryBookerEventSpotDto);
    }

    @Override
    public Optional<EventHistory> bookEventSpot(long eventSpotId) throws EventHistoryServiceException {
        User user = getConnectedUser();
        EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto =
                new EventHistoryBookerEventSpotDto(
                        null,
                        null,
                        user != null ? user.getUserId() : null,
                        user != null ? user.getUsername() : null,
                        EventStatus.BOOKED,
                        null,
                        eventSpotId,
                        null,
                        EventSpotStatus.BOOKED,
                        1L
                );
        return this.bookEventSpot(eventHistoryBookerEventSpotDto);
    }

    @Override
    public Optional<EventHistory> unbookEventSpot(long eventSpotId) throws EventHistoryServiceException {
        User user = getConnectedUser();
        EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto =
                new EventHistoryBookerEventSpotDto(
                        null,
                        null,
                        user != null ? user.getUserId() : null,
                        user != null ? user.getUsername() : null,
                        EventStatus.BOOKED,
                        null,
                        eventSpotId,
                        null,
                        EventSpotStatus.UNBOOKED,
                        -1L
                );
        return this.bookEventSpot(eventHistoryBookerEventSpotDto);
    }

    @Override
    public Optional<EventHistory> setEventStatus(EventHistoryStatusDto eventHistoryStatusDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryStatusDto);
    }

    @Override
    public List<EventHistory> findEventHistoryByEventId(EventHistoryEventIdDto eventHistoryEventIdDto) {
        return ((EventHistoryRepository)getDefaultRepository()).findEventHistoryByEventId(eventHistoryEventIdDto.eventId());
    }



    @Override
    public List<EventHistory> findAllByOrderByIdDesc(int currentPage, int elementPerPage) {
        Pageable pageable = Pageable.ofSize( elementPerPage).withPage( currentPage-1);
        return ((EventHistoryRepository)getDefaultRepository()).findByOrderByIdDesc(pageable);
    }

    @Override
    public List<EventHistory> findAllEventHistory() {
        return getDefaultRepository().findAll();
    }
}
