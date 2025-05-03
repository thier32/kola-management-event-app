package com.kola.management.event.event.dto.eventspot;

/**
 *
 * @param eventDescription
 * @param eventSpotId
 */
public record EventSpotUpdateDescritpionDto(
        String eventDescription,
        long eventSpotId
) implements IEventSpotDto {
}
