package com.kola.management.event.event.dto.eventspot;


/**
 *
 * @param eventSpotName
 * @param eventSpotId
 */
public record IEventSpotDtoUpdateNameDto(
        String eventSpotName,
        long eventSpotId
) implements IEventSpotDto {
}
