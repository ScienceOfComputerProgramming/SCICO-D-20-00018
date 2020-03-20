package com.gamaza.rest4cep.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Event Type Data Transfer Object
 */
@Getter @Setter @ToString
public class EventTypeMongoDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 5922044319748765104L;

    // Private variables
    private String id;
    private String name;
    private String content;
    private String createdDate;

}
