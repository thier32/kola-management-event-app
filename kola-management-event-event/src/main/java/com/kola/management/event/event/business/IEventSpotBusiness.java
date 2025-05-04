package com.kola.management.event.event.business;

import com.kola.management.event.event.business.exceptions.EventSpotBusinessException;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.ListDataDto;
import com.kola.management.event.event.dto.eventspot.*;
import com.kola.management.event.kernel.model.BaseKernelModel;

public interface IEventSpotBusiness {
    /**
     *
     * @param eventSpotDto eventSpotDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto createEventSpot(EventSpotDto eventSpotDto) throws EventSpotBusinessException;

    /**
     *
     * @param eventSpotDto eventSpotDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto addEventSpotToEvent(EventSpotDto eventSpotDto);

    /**
     *
     * @param eventSpotDto eventSpotDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto removeEventSpotToEvent(EventSpotDto eventSpotDto);

    /**
     *
     * @param eventSpotUpdateStatusDto eventSpotUpdateStatusDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto publishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto);

    /**
     *
     * @param eventSpotUpdateStatusDto eventSpotUpdateStatusDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto unpublishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto);

    /**
     *
     * @param eventSpotUpdateStatusDto eventSpotUpdateStatusDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto bookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto);

    /**
     *
     * @param eventSpotId eventSpotId
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto bookEventSpot(long eventSpotId) throws EventSpotBusinessException;


    EventSpotReturnDto unbookEventSpot(long eventSpotId) throws EventSpotBusinessException;

    /**
     *
     * @param eventSpotUpdateStatusDto eventSpotUpdateStatusDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto unbookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws EventSpotBusinessException;

    /**
     *
     * @param eventSpotUpdateDto eventSpotUpdateDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpot(EventSpotUpdateDto eventSpotUpdateDto);

    /**
     *
     * @param eventSpotUpdateNameDto eventSpotUpdateNameDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpotName(EventSpotUpdateNameDto eventSpotUpdateNameDto);

    /**
     *
     * @param eventSpotUpdateCapacityDto eventSpotUpdateCapacityDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpotCapacity(EventSpotUpdateCapacityDto eventSpotUpdateCapacityDto);

    /**
     *
     * @param eventSpotUpdateOccupationDto eventSpotUpdateStateDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpotOccupation(EventSpotUpdateOccupationDto eventSpotUpdateOccupationDto);

    /**
     *
     * @param eventSpotUpdateStatusDto eventSpotUpdateStateDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto);

    /**
     *
     * @param eventSpotUpdateStateDto eventSpotUpdateStateDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto updateEventSpotState(EventSpotUpdateStateDto eventSpotUpdateStateDto);


    /**
     *
     * @param model
     * @return
     * @throws EventSpotBusinessException
     */
    EventSpotReturnDto map(BaseKernelModel model) throws EventSpotBusinessException;

    /**
     *
     * @param pageNo
     * @return
     */
    ListDataDto<EventSpotReturnDto> getEventSpotListData(Integer pageNo);

    /**
     *
     * @return
     */
    ListDataDto<EventSpotReturnDto> getEventSpotListData();

    ListDataDto<EventSpotReturnDto> getEventSpotListData(int pageNo);

    EventSpotReturnDto getEventSpot(Long eventSpotId) throws EventSpotBusinessException;

    ListDataDto<EventSpotReturnDto> getEventEventSpots(Long eventId, Integer pageNo) throws EventSpotBusinessException;

    ListDataDto<EventSpotReturnDto> getEventEventSpots(Long eventId) throws EventSpotBusinessException;

}
