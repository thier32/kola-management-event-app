package com.kola.management.event.event.dto.eventspot;

import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.ListDataDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record EventSpotDto(
        String eventSpotName,
        String eventName,
        Long eventSpotId,
        Long eventId,
        Long eventSpotCapacity,
        Long eventSpotOccupation,
        String eventSpotImageUrl,
        MultipartFile eventSpotImage,
        List<EventReturnDto> listEventDto
) implements IEventSpotDto{
    public EventSpotDto(){
        this(null,null, null, null,null,null,null,null,null);
    }
    public EventSpotDto(String eventSpotName,long eventId){
        this(eventSpotName,null, null, eventId,null,null,null,null,null);
    }

    public EventSpotDto(String eventSpotName,long eventId,long eventSpotCapacity){
        this(eventSpotName,null, null, eventId,eventSpotCapacity,null,null,null,null);
    }

    public EventSpotDto(long eventId,long eventSpotOccupation){
        this(null,null, null, eventId,null,eventSpotOccupation,null,null,null);
    }

    public EventSpotDto(ListDataDto<EventReturnDto> eventReturnDtoList) {
        this(null,null, null,null,null,null,null,null,eventReturnDtoList.listElements);
    }
}
