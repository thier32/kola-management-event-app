package com.kola.management.event.event.dto.eventspot;

public record EventSpotDto(
        String eventSpotName,
        String eventName,
        long eventSpotId,
        long eventId
) implements IEventSpotDto{

}
