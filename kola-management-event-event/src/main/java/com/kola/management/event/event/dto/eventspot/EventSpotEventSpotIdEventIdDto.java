package com.kola.management.event.event.dto.eventspot;

public record EventSpotEventSpotIdEventIdDto(
        Long eventId,
        long eventSpotId
) implements IEventSpotDto{

}
