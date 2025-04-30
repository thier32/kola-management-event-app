package com.kola.management.event.event.services.eventhistory.impl;

import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventHistoryService implements IEventHistoryService {

    @Override
    public Optional<EventHistory> createEventHistory(EventHistoryDto eventHistoryDto) {
        return Optional.empty();
    }

    @Override
    public Optional<EventHistory> setEventStartDate(EventHistoryStartDateDto eventHistoryStartDateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<EventHistory> setEventEndDate(EventHistoryEndDateDto eventHistoryEndDateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<EventHistory> publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) {
        return Optional.empty();
    }

    @Override
    public Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) {
        return Optional.empty();
    }

    @Override
    public Optional<EventHistory> setEventStatus(EventHistoryStatusDto eventHistoryStatusDto) {
        return Optional.empty();
    }

    @Override
    public List<EventHistory> findEventHistoryByEventId(EventHistoryEventIdDto eventHistoryEventIdDto) {
        return List.of();
    }

    @Override
    public List<EventHistory> findEventHistoryByStatus(EventHistoryStatusDto eventHistoryStatusDto) {
        return List.of();
    }
}
