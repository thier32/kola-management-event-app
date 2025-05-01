package com.kola.management.event.event.dto.eventhistory;

/**
 *
 * @param eventId
 * @param eventName
 * @param bookerId
 * @param bookerName
 */
public record EventHistoryBookerEventDto(
        long eventId,
        String eventName,
        long bookerId,
        String bookerName,
        int status
) implements IEventHistoryDto {
}
