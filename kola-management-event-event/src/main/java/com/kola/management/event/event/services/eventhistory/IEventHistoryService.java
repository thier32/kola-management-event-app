package com.kola.management.event.event.services.eventhistory;

import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.EventHistory;

import java.util.List;
import java.util.Optional;

public interface IEventHistoryService {
    /**
     *
     * @param eventHistoryDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> createEventHistory(EventHistoryDto eventHistoryDto);

    /**
     *
     * @param eventHistoryStartDateDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventStartDate(EventHistoryStartDateDto eventHistoryStartDateDto);

    /**
     *
     * @param eventHistoryEndDateDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventEndDate(EventHistoryEndDateDto eventHistoryEndDateDto);

    /**
     *
     * @param eventHistoryPublisherEventDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto);

    /**
     *
     * @param eventHistoryBookerEventDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto);

    /**
     *
     * @param eventHistoryStatusDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> setEventStatus(EventHistoryStatusDto eventHistoryStatusDto);

    /**
     *
     * @param eventHistoryEventIdDto
     * @return List<EventHistory>
     */
   List<EventHistory> findEventHistoryByEventId(EventHistoryEventIdDto eventHistoryEventIdDto);   List<EventHistory> findEventHistoryByStatus(EventHistoryStatusDto eventHistoryStatusDto);
}
