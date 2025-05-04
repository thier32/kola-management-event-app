package com.kola.management.event.event.model;

import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event extends BaseKernelModel {

    public static final String eventIdProp = "eventId";
    public static final String eventNameProp = "eventName";
    public static final String eventVenueProp = "eventVenue";
    public static final String eventDescriptionProp = "eventDescription";
    public static final String eventImageUrlProp = "eventImageUrl";

    private long eventId;
    private String eventName;
    private String eventVenue;
    private String eventDescription;
    private String eventImageUrl;
}
