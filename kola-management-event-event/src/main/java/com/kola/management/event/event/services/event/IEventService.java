package com.kola.management.event.event.services.event;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.kernel.model.BaseKernelModel;

import java.util.List;
import java.util.Optional;

public interface IEventService {
   String NOT_FOUND_MESSAGE_TEMPLATE = "%s with %s %s not found.";

   /**
    *
    * @param eventDto
    * @param eventId
    * @return
    * @throws EventServiceException
    */
   Optional<Event> updateEvent(IEventDto eventDto, long eventId) throws EventServiceException;


   /**
    *
    * @param eventDto
    * @param eventId
    * @return
    * @throws EventServiceException
    */
   Event saveEventDto(IEventDto eventDto,Long eventId) throws EventServiceException;

   /**
    *
    * @param eventDto
    * @return
    * @throws EventServiceException
    */
   Optional<Event> saveEvent(IEventDto eventDto) throws EventServiceException;



   /**
    *
    * @param eventDto
    * @return
    * @throws EventServiceException
    */
   Event saveEventDto(IEventDto eventDto) throws EventServiceException;

   /**
    *
    * @param eventDto  eventDto
    * @return  Optional of <Event>
    */
   Optional<Event> createEvent(EventDto eventDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateDto  eventUpdateDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateStateDto  eventUpdateStateDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateNameDto  eventUpdateNameDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateDescritpinDto  eventUpdateDescritpinDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateNameDescriptionDto eventUpdateNameDescriptionDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException;

   /**
    *
    * @param eventEventIdDto  EventIdDto
    * @return  Optional of <Event>
    */
   Optional<Event> findEventByEventId(EventEventIdDto eventEventIdDto);

   /**
    *
    * @param eventEventIdDto EventIdDto
    */
   Event verifyEventExistByEventId(EventEventIdDto eventEventIdDto) throws EventServiceException;

   /**
    *
    * @param eventNameDto EventNameDto
    * @return  Optional of <Event>
    */
   List<Event> findEventByEventName(EventNameDto eventNameDto);

   /**
    *
    * @return list of event -> list can be empty
    */
   List<Event> findAllEvents();


   /**
    *
    * @param page page number
    * @param element number elements per page
    * @return list of event -> list can be empty
    */
   public List<Event> findAllByOrderByIdDesc(int page, int element);

   EventReturnDto mapping(BaseKernelModel model, Class<EventReturnDto> eventReturnDtoClass) throws EventServiceException;
}