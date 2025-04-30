package com.kola.management.event.event.dto.eventhistory;

/**
 *
 * @param eventId
 * @param eventName
 * @param publisherId
 * @param publisherName
 * @param eventStatus
 */
public record EventHistoryStatusDto(
        long eventId,
        String eventName,
        long publisherId,
        String publisherName,
        int eventStatus
) implements IEventHistoryDto {
}
