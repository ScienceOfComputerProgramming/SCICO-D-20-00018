package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Event Pattern Data Transfer Object for PUT operations
 */
@Getter @Setter
public class EventPatternPutDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -8765993002066871194L;

    // Private variables
    private String name;
    private String content;

}
