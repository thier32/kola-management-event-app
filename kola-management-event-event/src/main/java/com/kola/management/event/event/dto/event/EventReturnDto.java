package com.kola.management.event.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

