package com.kola.management.event.event.dto.eventspot;

/**
 *
 * @param eventSpotOccupation
 * @param eventSpotId
 */
public record EventSpotUpdateOccupationDto(
        long eventSpotOccupation,
        long eventSpotId
) implements IEventSpotDto {
}
