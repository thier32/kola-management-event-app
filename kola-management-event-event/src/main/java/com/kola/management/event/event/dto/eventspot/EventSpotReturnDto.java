package com.kola.management.event.event.dto.eventspot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

