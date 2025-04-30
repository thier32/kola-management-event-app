package com.kola.management.event.event.dto.eventhistory;

/**
 *
 * @param eventId
 * @param eventName
 * @param eventVenue
 * @param eventDescription
 * @param eventBookerId
 * @param eventBookerName
 */
public record EventHistoryDto(
        long eventId,
        String eventName,
        String eventVenue,
        String eventDescription,
        long eventBookerId,
        String eventBookerName
) implements IEventHistoryDto {
}
