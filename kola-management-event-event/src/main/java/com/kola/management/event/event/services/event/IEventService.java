package com.kola.management.event.event.services.event;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.kernel.services.IBaseKernelService;

import java.util.List;
import java.util.Optional;

public interface IEventService extends IBaseKernelService {
   String NOT_FOUND_MESSAGE_TEMPLATE = "%s with %s %s not found.";
   String NOT_ENOUGH_SPOT_MESSAGE_TEMPLATE = "There is no more spot for event %s.";
   String NO_SPOT_DEFINED_MESSAGE_TEMPLATE = "There is not spot defined for event %s.";

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
   Optional<Event> updateEvent(EventUpdateDto eventUpdateDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateStateDto  eventUpdateStateDto
    * @return  Optional of <Event>
    */
   Optional<Event> updateEventState(EventUpdateStateDto eventUpdateStateDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateNameDto  eventUpdateNameDto
    * @return  Optional of <Event>
    */
   Optional<Event> updateEventName(EventUpdateNameDto eventUpdateNameDto) throws EventServiceException;

   Optional<Event> updateEventStatus(EventUpdateStatusDto eventUpdateStatusDto) throws EventServiceException;

   Optional<Event> updateEventImage(EventUpdateImageUrlDto eventUpdateImageUrlDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateDescritpinDto  eventUpdateDescritpinDto
    * @return  Optional of <Event>
    */
   Optional<Event> updateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto) throws EventServiceException;

   /**
    *
    * @param eventUpdateNameDescriptionDto eventUpdateNameDescriptionDto
    * @return  Optional of <Event>
    */
   Optional<Event> updateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto) throws EventServiceException;

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


   List<Event> findAllEventsByStatus(List<EventStatus> eventStatus);

   /**
    *
    * @param page page number
    * @param element number elements per page
    * @return list of event -> list can be empty
    */
   List<Event> findAllByOrderByIdDesc(int page, int element);

   /**
    *
    * @param page
    * @param element
    * @return
    */
   List<EventReturnDto> findEventReturnDtoAllByOrderByIdDesc(int page, int element);

   List<EventReturnDto> findEventReturnDtoAllByOrderByIdDesc(List<EventStatus> eventStatuses,int page, int element);


   List<EventReturnDto> findEventReturnDtoAllByOrderByIdDesc();

   EventReturnDto mapping(BaseKernelModel model, Class<EventReturnDto> eventReturnDtoClass) throws EventServiceException;


   Optional<Event> updateEventOccupation(EventUpdateOccupationDto eventUpdateOccupationDto) throws EventServiceException;

}