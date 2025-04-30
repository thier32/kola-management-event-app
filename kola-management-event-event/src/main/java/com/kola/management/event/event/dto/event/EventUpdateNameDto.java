package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventId
 */
public record EventUpdateNameDto(
        String eventName,
        long eventId
) implements IEventDto {
}
