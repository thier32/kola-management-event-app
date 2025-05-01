package com.kola.management.event.event.business;

import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.EventUpdateDto;
import com.kola.management.event.event.dto.event.ListDataDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryBookerEventDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryEndDateDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryPublisherEventDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryStartDateDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.exceptions.EventServiceException;

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
     * @param eventHistoryPublisherEventDto
     * @return
     */
    EventReturnDto unPublishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto);

    /**
     *
     * @param eventHistoryBookerEventDto
     * @return
     */
    EventReturnDto bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto);

    /**
     *
     * @param eventHistoryBookerEventDto
     * @return
     */
    EventReturnDto unBookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto);

    /**
     *
     * @return
     */
    ListDataDto<Event> getListData();

    EventReturnDto getEvent(Long eventId) throws EventBusinessException;

//    List<Event> findAllEvents();

}
