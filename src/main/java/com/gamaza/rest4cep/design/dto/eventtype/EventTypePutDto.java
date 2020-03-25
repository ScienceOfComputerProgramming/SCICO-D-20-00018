package com.gamaza.rest4cep.design.dto.eventtype;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Event Type Data Transfer Object for PUT operations
 */
@Getter @Setter
public class EventTypePutDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 5054096826045066018L;

    // Private variables
    private String name;
    private String structure;
    private String description;

}
