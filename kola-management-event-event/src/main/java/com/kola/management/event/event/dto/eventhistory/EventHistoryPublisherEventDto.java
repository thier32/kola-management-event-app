package com.kola.management.event.event.dto.eventhistory;

import java.time.LocalDateTime;

/**
 *
 * @param eventId
 * @param eventName
 * @param publisherId
 * @param publisherName
 * @param eventStartDate
 * @param eventEndDate
 */
public record EventHistoryPublisherEventDto(
        long eventId,
        String eventName,
        long publisherId,
        String publisherName,
        boolean status,
        LocalDateTime eventStartDate,
        LocalDateTime eventEndDate
) implements IEventHistoryDto {
}
