package com.kola.management.event.event.dto.eventhistory;

import com.kola.management.event.event.dto.event.EventStatus;

public record EventHistoryChangeStatusEventDto(
        long eventId,
        String eventName,
        EventStatus eventStatus
) implements IEventHistoryDto {
}
