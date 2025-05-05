package com.kola.management.event.event.business.eventspot;

import com.kola.management.event.event.business.IEventSpotBusiness;
import com.kola.management.event.event.business.exceptions.EventSpotBusinessException;
import com.kola.management.event.event.dto.event.EventEventIdDto;
import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.event.dto.event.ListDataDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryBookerEventDto;
import com.kola.management.event.event.dto.eventspot.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventSpot;
import com.kola.management.event.event.services.event.impl.EventService;
import com.kola.management.event.event.services.eventhistory.IEventHistoryService;
import com.kola.management.event.event.services.eventspot.IEventSpotService;
import com.kola.management.event.event.services.exceptions.EventHistoryServiceException;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.exceptions.EventSpotServiceException;
import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventSpotBusiness implements IEventSpotBusiness {

    @Autowired
    IEventSpotService eventSpotService;

    @Autowired
    IEventHistoryService eventHistoryService;

    @Value("${kola.event.management.folder.eventspot.images}")
    private String eventSpotFolder;

    @Autowired
    private EventService eventService;



    @Override
    public EventSpotReturnDto createEventSpot(EventSpotDto eventSpotDto) throws EventSpotBusinessException {
        EventSpotReturnDto eventSpotReturnDto = null;
        try{
            Optional<EventSpot> optionalEventSpot;
            if (eventSpotDto.eventSpotId() != null){
                optionalEventSpot = eventSpotService.updateEventSpot(eventSpotDto,eventSpotDto.eventSpotId());
            }else{
                 Event event = eventService.verifyEventExistByEventId(new EventEventIdDto(eventSpotDto.eventId()));

                 EventSpotDto fullEventSpotDto = new EventSpotDto(
                         eventSpotDto.eventSpotName(),
                         event.getEventName(),
                         null,
                         event.getEventId(),
                         eventSpotDto.eventSpotCapacity() != null ? eventSpotDto.eventSpotCapacity() : 0L,
                         eventSpotDto.eventSpotOccupation() != null ? eventSpotDto.eventSpotOccupation() : 0L,
                         eventSpotDto.eventSpotImageUrl(),
                         eventSpotDto.eventSpotImage(),null
                 );
                optionalEventSpot = eventSpotService.createEventSpot(fullEventSpotDto);
            }

            if (optionalEventSpot.isPresent()){
                eventSpotReturnDto = map(optionalEventSpot.get());
            };
        }catch (EventSpotServiceException eventSpotServiceException){
            throw new EventSpotBusinessException(eventSpotServiceException.getMessage());
        } catch (EventServiceException eventServiceException) {
            throw new EventSpotBusinessException(eventServiceException.getMessage());
        }
        return eventSpotReturnDto;
    }

    @Override
    public EventSpotReturnDto addEventSpotToEvent(EventSpotDto eventSpotDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto removeEventSpotToEvent(EventSpotDto eventSpotDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto publishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto unpublishEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto bookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {

        return null;
    }

    public EventSpotReturnDto changeEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws EventSpotBusinessException {
        EventSpotReturnDto eventSpotReturnDto = null;
        try{
            Optional<EventSpot> optionalEventSpot = this.eventSpotService.updateEventSpotStatus(eventSpotUpdateStatusDto);
            if (optionalEventSpot.isPresent()){
                eventSpotReturnDto = this.map(optionalEventSpot.get());
            }
        }catch (EventSpotServiceException eventSpotServiceException){
                throw new EventSpotBusinessException(eventSpotServiceException.getMessage());
        }

        return eventSpotReturnDto;
    }


    @Override
    public EventSpotReturnDto bookEventSpot(long eventSpotId) throws EventSpotBusinessException {
        try {
            this.eventHistoryService.bookEventSpot(eventSpotId);
        } catch (EventHistoryServiceException e) {
            throw new EventSpotBusinessException(e.getMessage());
        }
        return this.changeEventSpotStatus(
                new EventSpotUpdateStatusDto(EventSpotStatus.BOOKED,eventSpotId,1L)
        );
    }

    @Override
    public EventSpotReturnDto unbookEventSpot(long eventSpotId) throws EventSpotBusinessException {
        try {
            this.eventHistoryService.unbookEventSpot(eventSpotId);
        } catch (EventHistoryServiceException e) {
            throw new EventSpotBusinessException(e.getMessage());
        }
        return this.changeEventSpotStatus(
                new EventSpotUpdateStatusDto(EventSpotStatus.UNBOOKED,eventSpotId,-1L)
        );
    }

    @Override
    public EventSpotReturnDto unbookEventSpot(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws EventSpotBusinessException {
        return this.changeEventSpotStatus(eventSpotUpdateStatusDto);
    }

    @Override
    public EventSpotReturnDto updateEventSpot(EventSpotUpdateDto eventSpotUpdateDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotName(EventSpotUpdateNameDto eventSpotUpdateNameDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotCapacity(EventSpotUpdateCapacityDto eventSpotUpdateCapacityDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotOccupation(EventSpotUpdateOccupationDto eventSpotUpdateOccupationDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) {
        return null;
    }

    @Override
    public EventSpotReturnDto updateEventSpotState(EventSpotUpdateStateDto eventSpotUpdateStateDto) {
        return null;
    }

    @Override
    public  EventSpotReturnDto map(BaseKernelModel model) throws EventSpotBusinessException {
        EventSpotReturnDto eventSpotReturnDto;
        try {
            eventSpotReturnDto =
                    eventSpotService.mapping(model, EventSpotReturnDto.class);
        }catch (EventSpotServiceException eventSpotServiceException){
            throw new EventSpotBusinessException(eventSpotServiceException.getMessage());
        }
        return eventSpotReturnDto;
    }

    @Override
    public ListDataDto<EventSpotReturnDto> getEventSpotListData(Integer pageNo) {
            if (pageNo != null){
                return getEventSpotListData(pageNo.intValue());
            }
            return getEventSpotListData();
    }

    @Override
    public ListDataDto<EventSpotReturnDto> getEventSpotListData() {
        return getEventSpotListData(1);
    }

    public ListDataDto<EventSpotReturnDto> getEventSpotListData(int pageNo , int elementPerPage, List<EventSpotReturnDto> elements, long size){
        ListDataDto<EventSpotReturnDto> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = pageNo;
        data.elementPerPage = elementPerPage;
        data.listElements = elements;
        data.total = size;
        long divisor = data.total;
        if (divisor <= 0){
            divisor = 1;
        }

        long reste = data.elementPerPage % divisor;
        long nbPage =  divisor / data.elementPerPage;
        if (reste != 0){
            nbPage++;
        }
        if (nbPage <= 0){
            nbPage = 1;
        }
        if (data.listElements.isEmpty()) data.listElements = null;
        data.numberPage = (int) nbPage;
        return data;
    }

    @Override
    public ListDataDto<EventSpotReturnDto> getEventSpotListData(int pageNo){
        ListDataDto<EventSpotReturnDto> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = pageNo;
        data.elementPerPage = 5;
        data.listElements = this.eventSpotService.findEventSpotReturnDtoAllByOrderByIdDesc(data.currentPage,data.elementPerPage);
        data.total = this.eventSpotService.findAllEventSpots().size();
        List<String> keys = new ArrayList<>();
        Arrays.stream(EventSpotStatus.values()).forEach(e -> keys.add(e.name()));
        data.keys = keys;
        long divisor = data.total;
        if (divisor <= 0){
            divisor = 1;
        }

        long reste = data.elementPerPage % divisor;
        long nbPage =  divisor / data.elementPerPage;
        if (reste != 0){
            nbPage++;
        }
        if (nbPage <= 0){
            nbPage = 1;
        }
        if (data.listElements.isEmpty()) data.listElements = null;
        data.numberPage = (int) nbPage;
        return data;
    }

    @Override
    public EventSpotReturnDto getEventSpot(Long eventSpotId) throws EventSpotBusinessException {
        Optional<EventSpot> optionalEventSpot = eventSpotService.findEventSpotByEventSpotId(new EventSpotEventSpotIdDto(eventSpotId));
        EventSpotReturnDto eventSpotReturnDto = null;
        if (optionalEventSpot.isPresent()){
            try {
                eventSpotReturnDto = eventSpotService.mapping(optionalEventSpot.get(),EventSpotReturnDto.class);
            }
            catch (KernelException kernelException)
            {
                throw new EventSpotBusinessException(kernelException.getMessage());
            }
        };
        assert eventSpotReturnDto != null;
        eventSpotReturnDto.setListEventDto(eventService.findEventReturnDtoAllByOrderByIdDesc());
        return eventSpotReturnDto;
    }

    @Override
    public ListDataDto<EventSpotReturnDto> getEventEventSpots(Long eventId, Integer pageNo) throws EventSpotBusinessException{
        try{
            Event event = eventService.verifyEventExistByEventId(new EventEventIdDto(eventId));
        }catch (EventServiceException eventServiceException){
            throw new EventSpotBusinessException(eventServiceException.getMessage());
        }
        if( pageNo == null){
            pageNo = 1;
        }
        int elementPerPage = 10;
        return getEventSpotListData(pageNo,elementPerPage,
                eventSpotService.findEventSpotReturnDtoAllByEventIdOrderByIdDesc(eventId,pageNo,
                        elementPerPage),
                eventSpotService.findEventSpotReturnDtoAllByEventIdOrderByIdDesc(eventId).size()
        );
    }

    @Override
    public ListDataDto<EventSpotReturnDto> getEventEventSpots(Long eventId) throws EventSpotBusinessException {
        return getEventEventSpots(eventId,null);
    }
}
