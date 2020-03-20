package com.gamaza.rest4cep.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Complex Events Detected Data Transfer Object
 */
@Getter @Setter @ToString
public class ComplexEventDetectedMongoDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 9086428828727064647L;

    // Private variables
    private String id;
    private String detectedBy;
    private String detectedEvent;
    private String createdDate;

}
