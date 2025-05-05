package com.kola.management.event.event.dto.eventspot;

/**
 *
 * @param eventSpotOccupation
 * @param eventSpotId
 */
public record EventSpotUpdateOccupationDto(
        Long eventSpotOccupation,
        long eventSpotId
) implements IEventSpotDto {
}
