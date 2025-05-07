package com.kola.management.event.event.dto.event;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
        MultipartFile eventImage,
        String selectedStartDate,
        LocalDateTime eventStartDate
) implements IEventDto {
    public EventDto(){
        this(null,null,null,null,null,null,null,null,null);
    }

    public EventDto(String eventName, String eventDescription,String eventVenue,String eventImageUrl){
        this(eventName,eventDescription,eventVenue,null,eventImageUrl,null,null,null,null);
    }
    public EventDto(String eventName, String eventDescription,String eventVenue){
        this(eventName,eventDescription,eventVenue,null,null,null,null,null,null);
    }
    public EventDto(String eventName, String eventDescription,String eventVenue,Long eventCapacity){
        this(eventName,eventDescription,eventVenue,null,null,eventCapacity,null,null,null);
    }
}

