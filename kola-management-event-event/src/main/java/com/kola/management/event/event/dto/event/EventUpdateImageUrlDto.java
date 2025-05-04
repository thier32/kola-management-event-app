package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventImageUrl
 * @param eventId
 */
public record EventUpdateImageUrlDto(
        String eventImageUrl,
        long eventId
) implements IEventDto {
}
