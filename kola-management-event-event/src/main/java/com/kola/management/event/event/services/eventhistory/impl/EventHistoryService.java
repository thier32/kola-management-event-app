package com.kola.management.event.event.services.eventhistory.impl;

import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.repository.EventHistoryRepository;
import com.kola.management.event.event.services.event.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventHistoryService extends BaseKernelService<EventHistory> implements IEventHistoryService {



    @Override
    public EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException {
        EventHistory eventHistory = null;
        try {
            eventHistory = this.mapping(eventHistoryDto, EventHistory.class);
            eventHistory = this.save(eventHistory);
        } catch (KernelException e) {
            throw new EventHistoryServiceException(e.getMessage());
        }
        return eventHistory;
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
    public Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) throws EventHistoryServiceException {
        return this.saveEventHistory(eventHistoryBookerEventDto);
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
    public List<EventHistory> findEventHistoryByStatus(EventHistoryStatusDto eventHistoryStatusDto) {
        return ((EventHistoryRepository)getDefaultRepository()).findEventHistoryByEventStatus(eventHistoryStatusDto.eventStatus());
    }
}
