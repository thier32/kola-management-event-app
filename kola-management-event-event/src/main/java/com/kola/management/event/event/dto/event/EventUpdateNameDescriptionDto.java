package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventDescription
 * @param eventId
 */
public record EventUpdateNameDescriptionDto(
        String eventName,
        String eventDescription,
        long eventId
) implements IEventDto {
}
