package com.kola.management.event.event.business;

import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.dto.eventhistory.EventHistoryEndDateDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryPublisherEventDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryStartDateDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventHistory;

import java.util.List;

public interface IEventBusiness {
    /**
     *
     * @param eventDto
     * @return
     */
    EventReturnDto createEvent(EventDto eventDto) throws  EventBusinessException;

    /**
     *
     * @param eventUpdateDto
     * @return
     */
    EventReturnDto updateEvent(EventUpdateDto eventUpdateDto);

    /**
     *
     * @param eventHistoryStartDateDto
     * @return
     */
    EventReturnDto updateStartEvent(EventHistoryStartDateDto eventHistoryStartDateDto);

    /**
     *
     * @param eventHistoryEndDateDto
     * @return
     */
    EventReturnDto updateEndEvent(EventHistoryEndDateDto eventHistoryEndDateDto);

    /**
     *
     * @param eventHistoryPublisherEventDto
     * @return
     */
    EventReturnDto publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto);


    /**
     *
     * @param event
     * @param eventStatus
     * @return
     * @throws EventBusinessException
     */
    EventReturnDto changeEventStatus(Event event, EventStatus eventStatus) throws EventBusinessException;


    /**
     *
     * @param eventId
     * @param eventStatus
     * @return
     * @throws EventBusinessException
     */
    public EventReturnDto changeEventStatus(long eventId, EventStatus eventStatus) throws EventBusinessException;
    /**
     *
     * @param eventId
     * @return
     */
    EventReturnDto publishEvent(long eventId) throws EventBusinessException;


    /**
     *
     * @param eventId
     * @return
     * @throws EventBusinessException
     */
    EventReturnDto unPublishEvent(long eventId) throws EventBusinessException;


    /**
     *
     * @return
     */
    ListDataDto<EventReturnDto> getEventListData();


    ListDataDto<EventReturnDto> getAllEventListData();

    /**
     *
     * @return
     */
    ListDataDto<EventReturnDto> getEventListData(Integer page);


    /**
     *
     * @param page
     * @param keys
     * @return
     */
    ListDataDto<EventReturnDto> getEventListData(Integer page, List<String> keys);

    /**
     *
     * @param eventId
     * @return
     * @throws EventBusinessException
     */
    EventReturnDto getEvent(Long eventId) throws EventBusinessException;

    /**
     *
     * @param eventId
     * @return
     */
    EventReturnDto bookEvent(long eventId) throws EventBusinessException;

    /**
     *
     * @param eventId
     * @return
     */
    EventReturnDto unBookEvent(long eventId) throws EventBusinessException;


    /**
     *
     * @return
     */
    ListDataDto<EventHistory> getEventHistoryListData();


    ListDataDto<EventHistory> getEventHistoryListData(int page);

    /**
     *
     * @return
     */
    ListDataDto<EventHistory> getEventHistoryListData(Integer pageNo);

}
