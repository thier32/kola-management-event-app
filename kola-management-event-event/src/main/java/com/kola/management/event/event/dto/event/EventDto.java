package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventDescription
 * @param eventVenue
 */
public record EventDto(
        String eventName,
        String eventDescription,
        String eventVenue
) implements IEventDto {
}
