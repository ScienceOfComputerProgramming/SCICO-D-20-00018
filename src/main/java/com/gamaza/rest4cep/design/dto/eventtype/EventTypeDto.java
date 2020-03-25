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
    private String name;
    private String structure;
    private String description;
    private boolean readyToDeploy;
    private boolean deployed;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

}
