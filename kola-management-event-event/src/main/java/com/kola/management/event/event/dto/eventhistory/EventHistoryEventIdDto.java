package com.kola.management.event.event.dto.eventhistory;

/**
 *
 * @param eventId
 */
public record EventHistoryEventIdDto(
        long eventId
) implements IEventHistoryDto {
}
