package com.gamaza.rest4cep.design.dto.eventtype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Event Type Data Transfer Object
 */
@Getter @Setter @ToString
public class EventTypeDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -8843543030824448187L;

    // Private variables
    private Integer id;
    private Integer channel;
    private String name;
    private String description;
    private boolean enabled;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

}
