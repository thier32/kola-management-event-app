package com.kola.management.event.event.repository;

import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.event.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends BaseKernelRepository<Event> {
    /**
     *
     * @param eventId  the eventId
     * @return Optional of Event
     */
    Optional<Event> findByEventId(long eventId);


    /**
     *
     * @param eventId
     * @return
     */
    Event findEventByEventId(long eventId);

    /**
     *
     * @param venue  the event's venue
     * @return List of Event -> list can be empty
     */
    List<Event> findEventByEventVenue(String venue);

    /**
     *
     * @param eventName the event name
     * @return List of Event -> list can be empty
     */
    List<Event> findByEventName(String eventName);

    /**
     *
     * @param pageable number of page and index
     * @return List of Event -> list can be empty
     */
    List<Event> findByOrderByIdDesc(Pageable pageable);

    @Query(value = "select event from Event  event where event.eventStatus in (:eventStatus)")
    List<Event> findEventByEventStatusList(@Param("eventStatus") List<EventStatus> eventStatus);

//    @Query(value = "select new com.kola.management.event.event.dto.event.EventReturnDto(event) from Event event")
//    List<EventReturnDto> findEventReturnDtoByOrderByIdDesc(Pageable pageable);

    @Query(value = "select new com.kola.management.event.event.dto.event.EventReturnDto(event,count(eventSpot)) from Event event " +
            "left join EventSpot eventSpot on eventSpot.eventId = event.eventId  group by event.eventId order by event.id desc ")
    List<EventReturnDto> findEventReturnDtoByOrderByIdDesc(Pageable pageable);

    @Query(value = "select new com.kola.management.event.event.dto.event.EventReturnDto(event,count(eventSpot)) from Event event " +
            "left join EventSpot eventSpot on eventSpot.eventId = event.eventId  group by event.eventId order by event.id desc")
    List<EventReturnDto> findEventReturnDtoByOrderByIdDesc();

    @Query(value = "select new com.kola.management.event.event.dto.event.EventReturnDto(event)" +
            " from Event event where event.eventStatus = :eventStatus order by event.id desc")
    List<EventReturnDto> findEventReturnDtoByEventStatusOrderByIdDesc(@Param("eventStatus") EventStatus eventStatus);

    @Query(value = "select new com.kola.management.event.event.dto.event.EventReturnDto(event)" +
            " from Event event where event.eventStatus in (:eventStatus) order by event.id desc")
    List<EventReturnDto> findEventReturnDtoByEventStatusOrderByIdDesc(@Param("eventStatus") List<EventStatus> eventStatus, Pageable pageable);
}
