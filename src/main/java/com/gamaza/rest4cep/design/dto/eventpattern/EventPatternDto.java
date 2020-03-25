package com.gamaza.rest4cep.design.dto.eventpattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Event Pattern Data Transfer Object
 */
@Getter @Setter @ToString
public class EventPatternDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 1121008118860307229L;

    // Private variables
    private Integer id;
    private String name;
    private String content;
    private boolean readyToDeploy;
    private boolean deployed;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

}
