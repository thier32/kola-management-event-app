package com.kola.management.event.event.services.eventhistory;

import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.event.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.event.exceptions.EventServiceException;

import java.util.List;
import java.util.Optional;

public interface IEventHistoryService {

    EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException;

    Optional<EventHistory> saveEventHistory(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryDto
     * @return
     */
   Optional<EventHistory> createEventHistory(EventHistoryDto eventHistoryDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryStartDateDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventStartDate(EventHistoryStartDateDto eventHistoryStartDateDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryEndDateDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventEndDate(EventHistoryEndDateDto eventHistoryEndDateDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryPublisherEventDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryBookerEventDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryStatusDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventStatus(EventHistoryStatusDto eventHistoryStatusDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryEventIdDto
     * @return List<EventHistory>
     */
   List<EventHistory> findEventHistoryByEventId(EventHistoryEventIdDto eventHistoryEventIdDto);   List<EventHistory> findEventHistoryByStatus(EventHistoryStatusDto eventHistoryStatusDto);
}
