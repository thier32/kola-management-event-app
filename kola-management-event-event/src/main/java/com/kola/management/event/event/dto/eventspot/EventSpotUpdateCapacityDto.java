package com.kola.management.event.event.dto.eventspot;

/**
 *
 * @param eventSpotCapacity
 * @param eventSpotId
 */
public record EventSpotUpdateCapacityDto(
        Long eventSpotCapacity,
        long eventSpotId
) implements IEventSpotDto {
}
