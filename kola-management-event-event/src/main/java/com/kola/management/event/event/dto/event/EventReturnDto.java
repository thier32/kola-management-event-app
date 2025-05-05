package com.kola.management.event.event.dto.event;

import com.kola.management.event.event.model.Event;
import com.kola.management.event.kernel.services.BaseKernelService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventReturnDto {
    long eventId;
    int status;
    String eventName;
    String eventVenue;
    String message;
    EventStatus eventStatus;
    String eventDescription;
    Date createdAt;
    String eventImageUrl;
    long eventSpotNumber = 0L;
    long eventBookedSpotNumber = 0L;
    Long eventCapacity;
    MultipartFile eventImage;

    public EventReturnDto(Event event, long eventspotnumber){
            eventId = event.getEventId();
            eventName = event.getEventName();
            eventVenue = event.getEventVenue();
            try {
                eventImageUrl = BaseKernelService.convertImageToBase64(event.getEventImageUrl());
            } catch (IOException e) {
            }
            eventDescription = event.getEventDescription();
            createdAt = event.getCreatedAt();
            this.eventSpotNumber = eventspotnumber;
            eventCapacity = event.getEventCapacity();
    }

    public EventReturnDto(Event event, long eventspotnumber,long eventbookedspotnumber){
        eventId = event.getEventId();
        eventName = event.getEventName();
        eventVenue = event.getEventVenue();
        eventImageUrl = event.getEventImageUrl();
        eventDescription = event.getEventDescription();
        createdAt = event.getCreatedAt();
        this.eventSpotNumber = eventspotnumber;
        this.eventBookedSpotNumber = eventbookedspotnumber;
        eventCapacity = event.getEventCapacity();
    }
}

