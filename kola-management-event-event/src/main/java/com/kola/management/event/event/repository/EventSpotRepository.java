package com.kola.management.event.event.repository;

import com.kola.management.event.event.dto.event.EventReturnDto;
import com.kola.management.event.event.dto.eventspot.EventSpotReturnDto;
import com.kola.management.event.event.model.Event;
import com.kola.management.event.event.model.EventSpot;
import com.kola.management.event.kernel.repository.BaseKernelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventSpotRepository extends BaseKernelRepository<EventSpot> {
    /**
     *
     * @param eventSpotId  the eventSpotId
     * @return Optional of EventSpot
     */
    Optional<EventSpot> findByEventSpotId(long eventSpotId);


    /**
     *
     * @param eventSpotId  the eventSpotId
     * @return Optional of Event spot
     */
    EventSpot findEventSpotByEventSpotId(long eventSpotId);


    /**
     *
     * @param eventId the eventId of the event
     *
     * @return list of spot of the provided event (eventid)
     */
    List<EventSpot> findEventSpotByEventId(long eventId);

    /**
     *
     * @param eventName the event name
     * @return List of EventSpot -> list can be empty
     */
    List<EventSpot> findByEventName(String eventName);

    /**
     *
     * @param pageable number of page and index
     * @return List of EventSpot -> list can be empty
     */
    List<EventSpot> findByOrderByIdDesc(Pageable pageable);

    /**
     *
     * @param capacity event spot capacity
     * @return List of EventSpot -> list can be empty
     */
    List<EventSpot> findEventSpotByEventSpotCapacity(long capacity);


    /**
     *
     * @param eventSpotName event spot name
     * @return List of EventSpot -> list can be empty
     */
    List<EventSpot> findEventSpotByEventSpotName(String eventSpotName);

    @Query(value = "select new com.kola.management.event.event.dto.eventspot.EventSpotReturnDto(eventSpot) from EventSpot eventSpot" +
            " order by eventSpot.id desc ")
    List<EventSpotReturnDto> findEventReturnDtoAllByOrderByIdDesc(Pageable pageable);


    @Query(value = "select new com.kola.management.event.event.dto.eventspot.EventSpotReturnDto(eventSpot) from EventSpot eventSpot" +
            " where eventSpot.eventId = :eventId  order by eventSpot.id desc ")
    List<EventSpotReturnDto> findEventReturnDtoAllByEventIdOrderByIdDesc(@Param("eventId") long eventId, Pageable pageable);


    @Query(value = "select new com.kola.management.event.event.dto.eventspot.EventSpotReturnDto(eventSpot) from EventSpot eventSpot" +
            " where eventSpot.eventId = :eventId  order by eventSpot.id desc ")
    List<EventSpotReturnDto> findEventReturnDtoAllByEventIdOrderByIdDesc(@Param("eventId") long eventId);
}
