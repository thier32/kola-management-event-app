package com.kola.management.event.event.dto.eventspot;

public record EventSpotEventSpotIdEventIdDto(
        long eventId,
        long eventSpotId
) implements IEventSpotDto{

}
