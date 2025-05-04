package com.kola.management.event.event.business;

import com.kola.management.event.event.dto.eventspot.*;

public interface IEventSpotBusiness {
    /**
     *
     * @param eventSpotDto eventSpotDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto createEventSpot(EventSpotDto eventSpotDto);

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
     * @param eventSpotUpdateStatusDto eventSpotUpdateStatusDto
     * @return EventSpotReturnDto
     */
    EventSpotReturnDto unbookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto);

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

}
