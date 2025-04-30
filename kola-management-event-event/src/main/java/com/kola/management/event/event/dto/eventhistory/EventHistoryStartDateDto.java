package com.kola.management.event.event.dto.eventhistory;

import java.time.LocalDateTime;

/**
 *
 * @param eventId
 * @param eventStartDate
 */
public record EventHistoryStartDateDto(
        long eventId,
        LocalDateTime eventStartDate
) implements IEventHistoryDto {
}
