package com.gamaza.rest4cep.mongo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * Complex Events Detected (Mongo version) Model
 */
@Document(collection = "complex_events_detected")
@Getter @Setter @EqualsAndHashCode
public class ComplexEventDetectedMongo {

    @Id
    private String id;

    @Field("detected_by")
    private String detectedBy;

    @Field("detected_event")
    private String detectedEvent;

    @Field("insertion_date")
    private String insertionDate;
}
