package com.gamaza.rest4cep.runtime.dto.incorrecteventtype;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Incorrect Event Type Data Transfer Object for POST operations
 */
@Getter @Setter
public class IncorrectEventTypePostDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -5776830616240406071L;

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Content can not be null")
    @NotBlank(message = "Content can not be blank")
    private String content;

}
