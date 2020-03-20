package com.gamaza.rest4cep.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

import static com.gamaza.rest4cep.config.constant.EntityConstants.COLLECTION_EVENT_PATTERN;

/**
 * Event Pattern Model
 */
@Document(collection = COLLECTION_EVENT_PATTERN)
@Getter @Setter
public class EventPatternMongo {

    @Id
    private String id;

    @Field(value = "name", order = 1)
    private String name;

    @Field(value = "content", order = 2)
    private String content;

    @CreatedDate
    @Field(value = "created_date", order = 3)
    private String createdDate;

}
