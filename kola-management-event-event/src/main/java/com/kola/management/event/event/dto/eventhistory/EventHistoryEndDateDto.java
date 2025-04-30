package com.kola.management.event.event.dto.eventhistory;

import java.time.LocalDateTime;

/**
 *
 * @param eventId
 * @param eventName
 * @param publisherId
 * @param publisherName
 * @param eventEndDate
 */
public record EventHistoryEndDateDto(
        long eventId,
        String eventName,
        long publisherId,
        String publisherName,
        LocalDateTime eventEndDate
) implements IEventHistoryDto {
}
