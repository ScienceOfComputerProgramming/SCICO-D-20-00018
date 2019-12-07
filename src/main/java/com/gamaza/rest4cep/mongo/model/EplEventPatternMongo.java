package com.gamaza.rest4cep.mongo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * EPL Event Patterns (Mongo version) Model
 */
@Document(collection = "epl_event_patterns")
@Getter @Setter @EqualsAndHashCode
public class EplEventPatternMongo {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("content")
    private String content;

    @Field("insertion_date")
    private String insertionDate;

}
