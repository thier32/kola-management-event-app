package com.kola.management.event.event.dto.eventspot;

public record EventSpotUpdateStateDto(long eventSpotId, EventSpotStatus eventSpotStatus) implements IEventSpotDto {
}
