package com.gamaza.rest4cep.runtime.dto.incorrecteventtype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Incorrect Event Type Data Transfer Object
 */
@Getter @Setter @ToString
public class IncorrectEventTypeDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 6652549327205635319L;

    // Private variables
    private String id;
    private String name;
    private String content;
    private String createdDate;

}
