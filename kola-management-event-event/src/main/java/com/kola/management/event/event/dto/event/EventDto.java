package com.kola.management.event.event.dto.event;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

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
        Long eventId,
        String eventImageUrl,
        Long eventCapacity,
        MultipartFile eventImage
) implements IEventDto {
    public EventDto(){
        this(null,null,null,null,null,null,null);
    }

    public EventDto(String eventName, String eventDescription,String eventVenue,String eventImageUrl){
        this(eventName,eventDescription,eventVenue,null,eventImageUrl,null,null);
    }
    public EventDto(String eventName, String eventDescription,String eventVenue){
        this(eventName,eventDescription,eventVenue,null,null,null,null);
    }
    public EventDto(String eventName, String eventDescription,String eventVenue,Long eventCapacity){
        this(eventName,eventDescription,eventVenue,null,null,eventCapacity,null);
    }
}

