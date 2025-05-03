package com.kola.management.event.event.dto.eventspot;


public record EventSpotUpdateNameDescriptionDto(
        String eventSpotName,
        String eventSpotDescription,
        long eventSpotId
) implements IEventSpotDto {
}
