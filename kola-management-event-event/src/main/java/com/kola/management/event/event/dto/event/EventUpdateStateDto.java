package com.kola.management.event.event.dto.event;

/**
 *
 * @param active
 * @param eventId
 */
public record EventUpdateStateDto(
        boolean active,
        long eventId
) implements IEventDto {
}
