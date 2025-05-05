package com.kola.management.event.event.services.eventhistory;

import com.kola.management.event.event.dto.eventhistory.*;
import com.kola.management.event.event.model.EventHistory;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;

import java.util.List;
import java.util.Optional;

public interface IEventHistoryService {


    /**
     *
     * @param eventHistoryDto
     * @param eventHistoryId
     * @return
     * @throws EventHistoryServiceException
     */
    EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto,Long eventHistoryId) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryDto
     * @return
     * @throws EventHistoryServiceException
     */
    EventHistory saveEventHistoryDto(IEventHistoryDto eventHistoryDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryDto
     * @return
     * @throws EventHistoryServiceException
     */
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
     * @param eventHistoryPublisherEventDto
     * @return
     * @throws EventHistoryServiceException
     */
    Optional<EventHistory> changeStatusEvent(EventHistoryChangeStatusEventDto eventHistoryPublisherEventDto) throws EventHistoryServiceException;


    Optional<EventHistory> findEventHistoryBychangeStatusEvent(EventHistoryChangeStatusEventDto eventHistoryPublisherEventDto) throws EventHistoryServiceException;

    /**
     *
     * @param eventHistoryBookerEventDto
     * @return Optional<EventHistory>
     */
   Optional<EventHistory> bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) throws EventHistoryServiceException;


   Optional<EventHistory> bookEventSpot(EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto) throws EventHistoryServiceException;


   Optional<EventHistory> unbookEventSpot(EventHistoryBookerEventSpotDto eventHistoryBookerEventSpotDto) throws EventHistoryServiceException;

   Optional<EventHistory> bookEventSpot(long eventSpotId) throws EventHistoryServiceException;

   Optional<EventHistory> unbookEventSpot(long eventSpotId) throws EventHistoryServiceException;

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

    /**
     *
     * @param currentPage
     * @param elementPerPage
     * @return
     */
   List<EventHistory> findAllByOrderByIdDesc(int currentPage, int elementPerPage);


    /**
     *
     * @return
     */
    List<EventHistory> findAllEventHistory();
}
