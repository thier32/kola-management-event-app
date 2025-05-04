package com.kola.management.event.event.dto.eventspot;


/**
 *
 * @param eventSpotStatus
 * @param eventSpotId
 */
public record EventSpotUpdateStatusDto(
        EventSpotStatus eventSpotStatus,
        long eventSpotId,
        Long eventId
) implements IEventSpotDto {
    public EventSpotUpdateStatusDto(EventSpotStatus eventSpotStatus, long eventSpotId){
        this(eventSpotStatus,eventSpotId,null);
    }
}
