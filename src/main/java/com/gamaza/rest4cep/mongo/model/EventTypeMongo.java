package com.gamaza.rest4cep.mongo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * Event Types (Mongo version) model
 */
@Document(collection = "event_types")
@Getter @Setter @EqualsAndHashCode
public class EventTypeMongo {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("content")
    private String content;

    @Field("insertion_date")
    private String insertionDate;

}
