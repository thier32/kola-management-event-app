package com.kola.management.event.event.dto.eventspot;

/**
 *
 * @param eventSpotName
 * @param eventSpotDescription
 * @param eventSpotId
 */
public record EventSpotUpdateDto(
        String eventSpotName,
        String eventSpotDescription,
        long eventSpotId
) implements IEventSpotDto {
}
