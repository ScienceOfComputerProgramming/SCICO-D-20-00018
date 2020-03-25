package com.gamaza.rest4cep.design.dto.eventtype;

import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Event Type Data Transfer Object (With Event Patterns List)
 */
@Getter @Setter @ToString
public class EventTypeWithListDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -1517315714926486239L;

    // Private variables
    private Integer id;
    private Integer channel;
    private String name;
    private String description;
    private boolean enabled;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private List<EventPatternDto> eventPatterns;

}

