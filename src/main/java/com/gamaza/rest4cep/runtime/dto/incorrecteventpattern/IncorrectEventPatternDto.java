package com.gamaza.rest4cep.runtime.dto.incorrecteventpattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Incorrect Event Pattern Data Transfer Object
 */
@Getter @Setter @ToString
public class IncorrectEventPatternDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -5111845235802529455L;

    // Private variables
    private String id;
    private String name;
    private String content;
    private String createdDate;

}
