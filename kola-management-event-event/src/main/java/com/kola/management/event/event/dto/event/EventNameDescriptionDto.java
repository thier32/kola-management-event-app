package com.kola.management.event.event.dto.event;

/**
 *
 * @param eventName
 * @param eventDescription
 */
public record EventNameDescriptionDto(
        String eventName,
        String eventDescription
) implements IEventDto {
}
