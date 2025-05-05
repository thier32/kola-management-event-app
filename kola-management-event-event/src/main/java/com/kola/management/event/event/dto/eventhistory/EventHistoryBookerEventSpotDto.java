package com.kola.management.event.event.dto.eventhistory;

import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.event.dto.eventspot.EventSpotStatus;

/**
 *
 * @param eventId
 * @param eventName
 * @param eventSpotBookerId
 * @param eventSpotBookerName
 * @param eventStatus
 * @param status
 * @param eventSpotId
 * @param eventSpotName
 * @param eventSpotStatus
 */
public record EventHistoryBookerEventSpotDto(
        Long eventId,
        String eventName,
        Long eventSpotBookerId,
        String eventSpotBookerName,
        EventStatus eventStatus,
        Integer status,
        long eventSpotId,
        String eventSpotName,
        EventSpotStatus eventSpotStatus,
        Long eventSpotOccupation
) implements IEventHistoryDto {
}
