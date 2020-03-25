package com.gamaza.rest4cep.runtime.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

import static com.gamaza.rest4cep.config.constant.EntityConstants.COLLECTION_COMPLEX_EVENTS_DETECTED;

/**
 * Complex Events Detected Model
 */
@Document(collection = COLLECTION_COMPLEX_EVENTS_DETECTED)
@Getter @Setter
public class ComplexEventDetected {

    @Id
    private String id;

    @Field(value = "detected_by", order = 1)
    private String detectedBy;

    @Field(value = "detected_event", order = 2)
    private String detectedEvent;

    @CreatedDate
    @Field(value = "created_date", order = 3)
    private String createdDate;

}
