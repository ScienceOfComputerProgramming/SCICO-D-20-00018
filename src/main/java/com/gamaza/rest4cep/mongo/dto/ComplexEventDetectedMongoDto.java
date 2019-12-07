package com.gamaza.rest4cep.mongo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Complex Events Detected (Mongo version) DTO
 */
@Getter @Setter @EqualsAndHashCode @ToString
public class ComplexEventDetectedMongoDto {

    private String id;

    @NotNull
    private String detectedBy;

    @NotNull
    private String detectedEvent;

    @NotNull
    private String insertionDate;

}
