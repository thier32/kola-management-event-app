package com.kola.management.event.event.repository;

import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.event.model.EventHistory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventHistoryRepository extends BaseKernelRepository<EventHistory> {
    /**
     *
     * @param eventHistoryId  the corresponding eventHistoryId
     * @return  Optional of EventHistory
     */
    Optional<EventHistory> findEventHistoryByEventHistoryId(long eventHistoryId);

    /**
     *
     * @param eventId  the corresponding eventId
     * @return  List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventId(long eventId);

    /**
     *
     * @param bookerId  the bookerId (the user who books the event)
     * @return  List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventBookerId(long bookerId);

    /**
     *
     * @param bookerName the bookerId (the user who booked the event)
     * @return  List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventBookerName(String bookerName);

    /**
     *
     * @param publisherName the publisherName (the admin who published the event)
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventPublisherName(String publisherName);

    /**
     *
     * @param publisherId the publisherId (the admin who published the event)
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventPublisherId(long publisherId);

    /**
     *
     * @param startDate the start of events
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventStartDate(LocalDateTime startDate);

    /**
     *
     * @param endDate the end date of events
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventEndDate(LocalDateTime endDate);

    /**
     *
     * @param eventEndDateAfter the end date (opening bracket) of events
     * @param eventEndDateBefore the end date (closing bracket) of events
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventEndDateBetween(LocalDateTime eventEndDateAfter, LocalDateTime eventEndDateBefore);

    /**
     *
     * @param eventStartDateAfter the start date (opening bracket) of events
     * @param eventStartDateBefore the start date (closing bracket) of events
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventStartDateBetween(LocalDateTime eventStartDateAfter, LocalDateTime eventStartDateBefore);

    /**
     *
     * @param status the requested event status
     * @return List of EventHistory -> list can be empty
     */
    List<EventHistory> findEventHistoryByEventStatus(int status);

    Optional<EventHistory> findFirstByEventIdAndEventStatusOrderByIdDesc(long eventId, EventStatus eventStatus);

}
