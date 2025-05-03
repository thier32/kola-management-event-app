package com.kola.management.event.event.model;

import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventHistory extends BaseKernelModel {

    public static final String eventHistoryIdProp = "eventHistoryId";
    public static final String eventIdProp = "eventId";
    public static final String eventNameProp = "eventName";
    public static final String eventVenueProp = "eventVenue";
    public static final String eventCapacityProp = "eventCapacity";
    public static final String eventStartDateProp = "eventStartDate";
    public static final String eventEndDateProp = "eventEndDate";
    public static final String eventStatusProp = "eventStatus";
    public static final String eventPublisherIdProp = "eventPublisherId";
    public static final String eventPublisherNameProp = "eventPublisherName";
    public static final String eventBookerIdProp = "eventBookerId";
    public static final String eventBookerNameProp = "eventBookerName";


    private long eventHistoryId;
    private long eventId;
    private String eventName;
    private String eventVenue;
    private String eventDescription;
    private long eventBookerId;
    private String eventBookerName;
    private long eventPublisherId;
    private String eventPublisherName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private long eventSpotId;
    private String eventSpotName;
    private long eventSpotCapacity;
}
