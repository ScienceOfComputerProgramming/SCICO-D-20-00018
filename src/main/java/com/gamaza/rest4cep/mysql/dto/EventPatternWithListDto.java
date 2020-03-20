package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Event Pattern Data Transfer Object (With Event Types List)
 */
@Getter @Setter @ToString
public class EventPatternWithListDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -2198442640221596485L;

    // Private variables
    private Integer id;
    private String name;
    private String content;
    private boolean readyToDeploy;
    private boolean deployed;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private List<EventTypeDto> eventTypes;

}