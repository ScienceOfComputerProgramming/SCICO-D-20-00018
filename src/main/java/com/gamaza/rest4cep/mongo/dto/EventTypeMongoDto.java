package com.gamaza.rest4cep.mongo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Event Types (Mongo version) DTO
 */
@Getter @Setter @EqualsAndHashCode @ToString
public class EventTypeMongoDto {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private String insertionDate;

}
