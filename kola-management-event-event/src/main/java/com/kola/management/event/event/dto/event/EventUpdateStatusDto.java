package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventStatus
 * @param eventId
 */
public record EventUpdateStatusDto(
        EventStatus eventStatus,
        long eventId
) implements IEventDto {
}
