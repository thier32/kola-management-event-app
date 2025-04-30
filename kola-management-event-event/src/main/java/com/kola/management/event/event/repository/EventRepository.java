package com.kola.management.event.event.repository;

import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.event.model.Event;
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

}
