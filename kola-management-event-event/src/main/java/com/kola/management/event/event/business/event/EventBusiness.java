package com.kola.management.event.event.business.event;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.EventUpdateDto;
import com.kola.management.event.event.dto.event.ListDataDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryBookerEventDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryEndDateDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryPublisherEventDto;
import com.kola.management.event.event.dto.eventhistory.EventHistoryStartDateDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.event.impl.EventService;
import com.kola.management.event.kernel.exception.KernelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventBusiness implements IEventBusiness {

    @Autowired
    EventService eventService;

    @Override
    public EventReturnDto createEvent(EventDto eventDto) throws EventBusinessException {
        EventReturnDto eventReturnDto = null;
        try{
            Optional<Event> optionalEvent = eventService.createEvent(eventDto);

            if (optionalEvent.isPresent()){
                eventReturnDto = map(optionalEvent.get());
            };
        }catch (EventServiceException|EventBusinessException eventServiceException){
            throw new EventBusinessException(eventServiceException.getMessage());
        }

        return eventReturnDto;
    }

    EventReturnDto map(Event event) throws EventBusinessException {
        EventReturnDto eventReturnDto;
        try {
            eventReturnDto =
                    eventService.mapping(event,EventReturnDto.class);
        }catch (KernelException kernelException){
            throw new EventBusinessException(kernelException.getMessage());
        }
        return eventReturnDto;
    }


    @Override
    public EventReturnDto updateEvent(EventUpdateDto eventUpdateDto) {
        return null;
    }

    @Override
    public EventReturnDto updateStartEvent(EventHistoryStartDateDto eventHistoryStartDateDto) {
        return null;
    }

    @Override
    public EventReturnDto updateEndEvent(EventHistoryEndDateDto eventHistoryEndDateDto) {
        return null;
    }

    @Override
    public EventReturnDto publishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) {
        return null;
    }

    @Override
    public EventReturnDto unPublishEvent(EventHistoryPublisherEventDto eventHistoryPublisherEventDto) {
        return null;
    }

    @Override
    public EventReturnDto bookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) {
        return null;
    }

    @Override
    public EventReturnDto unBookEvent(EventHistoryBookerEventDto eventHistoryBookerEventDto) {
        return null;
    }

    @Override
    public ListDataDto<Event> getListData() {
        ListDataDto<Event> data = new ListDataDto<>();
        data.numberPage = 10;
        data.currentPage = 1;
        data.elementPerPage = 10;
        data.listElements = this.eventService.findAllEvents();
//        data.listeElements = findAllBySlug(key, requestData.currentPage,requestData.numberPage);
        data.total = data.listElements.size();

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
        data.numberPage = (int) nbPage;
        return data;
    }

//    @Override
//    public List<Event> findAllEvents() {
//        return eventService.findAllEvents();
//    }
}
