package com.kola.management.event.event.dto.eventspot;

public record EventSpotDto(
        String eventSpotName,
        String eventName,
        Long eventSpotId,
        Long eventId,
        Long eventSpotCapacity,
        Long eventSpotOccupation
) implements IEventSpotDto{
    public EventSpotDto(){
        this(null,null, 0L, 0L,0L,0L);
    }
    public EventSpotDto(String eventSpotName,long eventId){
        this(eventSpotName,null, 0L, eventId,0L,0L);
    }

    public EventSpotDto(String eventSpotName,long eventId,long eventSpotCapacity){
        this(eventSpotName,null, 0L, eventId,eventSpotCapacity,0L);
    }

    public EventSpotDto(long eventId,long eventSpotOccupation){
        this(null,null, 0L, eventId,0L,eventSpotOccupation);
    }
}
