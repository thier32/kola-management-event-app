package com.kola.management.event.event.dto.eventspot;

import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.model.EventSpot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventSpotReturnDto {
    long eventId;
    int status;
    String eventName;
    String eventSpotName;
    long eventSpotCapacity;
    long eventSpotId;
    String message;
    EventSpotStatus eventSpotStatus;
    List<EventReturnDto> listEventDto;
    String eventSpotImage;


    public EventSpotReturnDto(EventSpot eventSpot){
        eventId = eventSpot.getEventId();
        eventSpotId = eventSpot.getEventSpotId();
        eventName = eventSpot.getEventName();
        eventSpotStatus = eventSpot.getEventSpotStatus();
        eventSpotName = eventSpot.getEventSpotName();
        eventSpotCapacity = eventSpot.getEventSpotCapacity();
        listEventDto = new ArrayList<>(0);
    }
}

