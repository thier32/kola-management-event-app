package com.kola.management.event.event.dto.event;

import com.kola.management.event.event.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    String eventImageUrl;
    long eventSpotNumber = 0L;
    long eventBookedSpotNumber = 0L;
    MultipartFile eventImage;

    public EventReturnDto(Event event, long eventspotnumber){
            eventId = event.getEventId();
            eventName = event.getEventName();
            eventVenue = event.getEventVenue();
            eventImageUrl = event.getEventImageUrl();
            eventDescription = event.getEventDescription();
            this.eventSpotNumber = eventspotnumber;
    }
    public EventReturnDto(Event event, long eventspotnumber,long eventbookedspotnumber){
        eventId = event.getEventId();
        eventName = event.getEventName();
        eventVenue = event.getEventVenue();
        eventImageUrl = event.getEventImageUrl();
        eventDescription = event.getEventDescription();
        this.eventSpotNumber = eventspotnumber;
        this.eventBookedSpotNumber = eventbookedspotnumber;
    }
}

