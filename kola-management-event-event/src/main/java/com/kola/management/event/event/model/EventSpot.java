package com.kola.management.event.event.model;

import com.kola.management.event.event.dto.eventspot.EventSpotStatus;
import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSpot extends BaseKernelModel {

    public static final String eventSpotIdProp = "eventSpotId";
    public static final String eventIdProp = "eventId";
    public static final String eventSpotNameProp = "eventSpotName";
    public static final String eventSpotCapacityProp = "eventSpotCapacity";
    public static final String eventSpotOccupationProp = "eventSpotOccupation";
    public static final String eventSpotDescriptionProp = "eventSpotDescription";
    public static final String eventSpotStatusProp = "eventSpotStatus";
    public static final String eventSpotImageUrlProp = "eventSpotImageUrl";

    private long eventSpotId;
    private long eventId;
    private long eventSpotCapacity;
    private long eventSpotOccupation;
    private String eventSpotName;
    private String eventName;
    private String eventSpotDescription;
    private EventSpotStatus eventSpotStatus;
    private String eventSpotImageUrl;
}
