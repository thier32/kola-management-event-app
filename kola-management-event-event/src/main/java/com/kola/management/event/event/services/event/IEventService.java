package com.kola.management.event.event.services.event;

import com.kola.management.event.event.dto.event.*;
import com.kola.management.event.event.model.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {
   /**
    *
    * @param eventDto  eventDto
    * @return  Optional of <Event>
    */
   Optional<Event> createEvent(EventDto eventDto);

   /**
    *
    * @param eventUpdateDto  eventUpdateDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEvent(EventUpdateDto eventUpdateDto);

   /**
    *
    * @param eventUpdateStateDto  eventUpdateStateDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventState(EventUpdateStateDto eventUpdateStateDto);

   /**
    *
    * @param eventUpdateNameDto  eventUpdateNameDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventName(EventUpdateNameDto eventUpdateNameDto);

   /**
    *
    * @param eventUpdateDescritpinDto  eventUpdateDescritpinDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventDescription(EventUpdateDescritpinDto eventUpdateDescritpinDto);

   /**
    *
    * @param eventUpdateNameDescriptionDto eventUpdateNameDescriptionDto
    * @return  Optional of <Event>
    */
   Optional<Event> UpdateEventNameDescription(EventUpdateNameDescriptionDto eventUpdateNameDescriptionDto);

   /**
    *
    * @param eventEventIdDto  EventIdDto
    * @return  Optional of <Event>
    */
   Optional<Event> findEventByEventId(EventEventIdDto eventEventIdDto);

   /**
    *
    * @param eventNameDto EventNameDto
    * @return  Optional of <Event>
    */
   List<Event> findEventByEventName(EventNameDto eventNameDto);
}