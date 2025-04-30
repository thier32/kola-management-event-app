package com.kola.management.event.event.dto.eventhistory;

/**
 *
 * @param eventId
 * @param eventName
 * @param publisherId
 * @param publisherName
 * @param eventVenue
 */
public record EventHistoryVenueDto(
        long eventId,
        String eventName,
        long publisherId,
        String publisherName,
        String eventVenue
) implements IEventHistoryDto {
}
