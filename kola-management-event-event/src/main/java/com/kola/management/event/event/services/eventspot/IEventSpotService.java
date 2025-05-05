package com.kola.management.event.event.services.eventspot;

import com.kola.management.event.event.dto.eventspot.*;
import com.kola.management.event.event.model.EventSpot;
import com.kola.management.event.event.services.exceptions.EventServiceException;
import com.kola.management.event.event.services.exceptions.EventSpotServiceException;
import com.kola.management.event.kernel.model.BaseKernelModel;

import java.util.List;
import java.util.Optional;

public interface IEventSpotService {
    String NOT_FOUND_MESSAGE_TEMPLATE = "%s with %s %s not found.";


    /**
     *
     * @param eventSpotDto
     * @param eventSpotId
     * @return
     * @throws EventSpotServiceException
     */
    Optional<EventSpot> updateEventSpot(IEventSpotDto eventSpotDto, long eventSpotId) throws EventSpotServiceException;


    /**
     *
     * @param eventSpotDto
     * @param eventSpotId
     * @return
     * @throws EventSpotServiceException
     */
    EventSpot saveEventSpotDto(IEventSpotDto eventSpotDto,Long eventSpotId) throws EventSpotServiceException;

    /**
     *
     * @param EventSpotDto
     * @return
     * @throws EventServiceException
     */
    Optional<EventSpot> saveEventSpot(IEventSpotDto EventSpotDto) throws EventSpotServiceException;


    /**
     *
     * @param eventSpotDto
     * @return
     * @throws EventSpotServiceException
     */
    EventSpot saveEventSpotDto(IEventSpotDto eventSpotDto) throws EventSpotServiceException;


    /**
     *
     * @param eventSpot
     * @return
     * @throws EventSpotServiceException
     */
    Optional<EventSpot> createEventSpot(EventSpotDto eventSpot) throws EventSpotServiceException;


    /**
     *
     * @param eventSpotDto
     * @return
     * @throws EventSpotServiceException
     */
    EventSpot saveEventSpotDto(EventSpotDto eventSpotDto) throws EventSpotServiceException;


    /**
     *
     * @param eventSpotUpdateDto
     * @return Optional of <EventSpot>
     *
     * @throws EventSpotServiceException
     */
    Optional<EventSpot> UpdateEventSpot(EventSpotUpdateDto eventSpotUpdateDto) throws EventSpotServiceException;

    /**
     *
     * @param eventUpdateStateDto  eventUpdateStateDto
     * @return  Optional of <Event>
     */
    Optional<EventSpot> UpdateEventSpotState(EventSpotUpdateStateDto eventUpdateStateDto) throws  EventSpotServiceException;

    /**
     *
     * @param eventSpotUpdateOccupationDto  eventSpotUpdateOccupationDto
     * @return  Optional of <Event>
     */
    Optional<EventSpot> UpdateEventSpotOccupation(EventSpotUpdateOccupationDto eventSpotUpdateOccupationDto) throws EventSpotServiceException;


    /**
     *
     * @param eventUpdateNameDto  eventUpdateNameDto
     * @return  Optional of <EventSpot>
     */
    Optional<EventSpot> UpdateEventSpotName(EventSpotUpdateNameDto eventUpdateNameDto) throws EventServiceException, EventSpotServiceException;


    /**
     *
     * @param eventUpdateDescritpinDto
     * @return
     * @throws EventServiceException
     */
    Optional<EventSpot> UpdateEventSpotDescription(EventSpotUpdateDescritpionDto eventUpdateDescritpinDto) throws  EventSpotServiceException;


    /**
     *
     * @param eventSpotUpdateStatusDto
     * @return
     * @throws EventSpotServiceException
     */
    Optional<EventSpot> updateEventSpotStatus(EventSpotUpdateStatusDto eventSpotUpdateStatusDto) throws  EventSpotServiceException;


    void checkEventSpotOccupation(long evenSpotId, Long occupation) throws EventSpotServiceException;

    /**
     *
     * @param eventSpotEventSpotIdDto
     * @return
     */
    Optional<EventSpot> findEventSpotByEventSpotId(EventSpotEventSpotIdDto eventSpotEventSpotIdDto);


    EventSpot verifyEventSpotExistByEventSpotId(EventSpotEventSpotIdDto eventSpotEventSpotIdDto) throws EventSpotServiceException;


    /**
     *
     * @return list of event -> list can be empty
     */
    List<EventSpot> findAllEventSpots();


    /**
     *
     * @param page page number
     * @param element number elements per page
     * @return list of eventSpot -> list can be empty
     */
    public List<EventSpot> findAllByOrderByIdDesc(int page, int element);


    Optional<EventSpot> findEventSpotByEventSpotId(EventSpotEventSpotIdEventIdDto eventSpotIdEventIdDto);

      /**
     *
     * @param eventSpotNameDto Event spot name
     * @return list of eventSpot -> list can be empty
     */
    List<EventSpot> findEventByEventName(EventSpotNameDto eventSpotNameDto);

    Optional<EventSpot> verifyEventSpotExistByEventId(EventSpotEventSpotIdEventIdDto eventSpotIdEventIdDto) throws EventServiceException, EventSpotServiceException;

    EventSpotReturnDto mapping(BaseKernelModel model, Class<EventSpotReturnDto> eventSpotReturnDtoClass) throws EventSpotServiceException;

    List<EventSpotReturnDto> findEventSpotReturnDtoAllByOrderByIdDesc(int currentPage, int elementPerPage);


    List<EventSpotReturnDto> findEventSpotReturnDtoAllByEventIdOrderByIdDesc(long eventId, int currentPage, int elementPerPage);

    List<EventSpotReturnDto> findEventSpotReturnDtoAllByEventIdOrderByIdDesc(long eventId);
}
