package com.gamaza.rest4cep.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Event Pattern Data Transfer Object
 */
@Getter @Setter @ToString
public class EventPatternMongoDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -5111845235802529455L;

    // Private variables
    private String id;
    private String name;
    private String content;
    private String createdDate;

}
