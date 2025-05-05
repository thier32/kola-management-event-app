package com.kola.management.event.event.model;

import com.kola.management.event.event.dto.event.EventStatus;
import com.kola.management.event.event.dto.eventspot.EventSpotStatus;
import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Column;
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
    public static final String eventSpotStatusProp = "eventSpotStatus";


    private long eventHistoryId;
    @Column(nullable = true)
    private Long eventId;
    @Column(nullable = true)
    private String eventName;
    @Column(nullable = true)
    private String eventVenue;
    @Column(nullable = true)
    private String eventDescription;
    @Column(nullable = true)
    private Long eventBookerId;
    @Column(nullable = true)
    private String eventBookerName;
    @Column(nullable = true)
    private Long eventPublisherId;
    @Column(nullable = true)
    private String eventPublisherName;
    @Column(nullable = true)
    private String eventSpotBookerName;
    @Column(nullable = true)
    private Long eventSpotPublisherId;
    @Column(nullable = true)
    private String eventSpotPublisherName;
    @Column(nullable = true)
    private Long eventSpotBookerId;
    private EventStatus eventStatus;
    private EventSpotStatus eventSpotStatus;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    @Column(nullable = true)
    private Long eventSpotId;
    @Column(nullable = true)
    private String eventSpotName;
    @Column(nullable = true)
    private Long eventSpotCapacity;
}
