package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventId
 * @param eventOccupation
 */
public record EventUpdateOccupationDto(
        long eventId,
        long eventOccupation
) implements IEventDto {
}
