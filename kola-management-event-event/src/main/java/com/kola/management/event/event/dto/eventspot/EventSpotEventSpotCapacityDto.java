package com.kola.management.event.event.dto.eventspot;

public record EventSpotEventSpotCapacityDto(
        long eventId,
        Long capacity
) implements IEventSpotDto{
}
