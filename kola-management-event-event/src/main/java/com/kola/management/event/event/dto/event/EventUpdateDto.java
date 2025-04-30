package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventDescription
 * @param eventVenue
 * @param eventId
 */
public record EventUpdateDto(
        String eventName,
        String eventDescription,
        String eventVenue,
        long eventId
) implements IEventDto {
}
