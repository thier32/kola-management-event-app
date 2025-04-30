package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventId
 */
public record EventUpdateDescritpinDto(
        String eventName,
        long eventId
) implements IEventDto {
}
