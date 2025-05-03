package com.kola.management.event.event.dto.eventspot;


/**
 *
 * @param eventSpotStatus
 * @param eventSpotId
 */
public record EventSpotUpdateStatusDto(
        EventSpotStatus eventSpotStatus,
        long eventSpotId
) implements IEventSpotDto {
}
