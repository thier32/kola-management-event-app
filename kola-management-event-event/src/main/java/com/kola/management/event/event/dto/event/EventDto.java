package com.kola.management.event.event.dto.event;

import org.springframework.lang.Nullable;

/**
 *
 * @param eventName
 * @param eventDescription
 * @param eventVenue
 */
public record EventDto(
        String eventName,
        String eventDescription,
        String eventVenue,
        Long eventId
) implements IEventDto {
    public EventDto(){
        this(null,null,null,null);
    }
    public EventDto(String eventName, String eventDescription,String eventVenue){
        this(eventName,eventDescription,eventVenue,null);
    }
}

