package com.kola.management.event.event.dto.eventspot;


import com.kola.management.event.user.model.User;

/**
 *
 * @param eventSpotStatus
 * @param eventSpotId
 */
public record EventSpotUpdateStatusDto(
        EventSpotStatus eventSpotStatus,
        long eventSpotId,
        Long eventId,
        User user,
        Long eventSpotOccupation
) implements IEventSpotDto {
    public EventSpotUpdateStatusDto(EventSpotStatus eventSpotStatus, long eventSpotId){
        this(eventSpotStatus,eventSpotId,null,null,null);
    }
    public EventSpotUpdateStatusDto(EventSpotStatus eventSpotStatus, long eventSpotId,User user){
        this(eventSpotStatus,eventSpotId,null,null,null);
    }
    public EventSpotUpdateStatusDto(EventSpotStatus eventSpotStatus, long eventSpotId,Long occupation){
        this(eventSpotStatus,eventSpotId,null,null,occupation);
    }
}
