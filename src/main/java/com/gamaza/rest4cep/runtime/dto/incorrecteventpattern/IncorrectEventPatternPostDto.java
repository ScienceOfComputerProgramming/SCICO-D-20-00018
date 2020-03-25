package com.gamaza.rest4cep.runtime.dto.incorrecteventpattern;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Incorrect Event Pattern Data Transfer Object for POST operations
 */
@Getter @Setter
public class IncorrectEventPatternPostDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -8736090114722151483L;

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Content can not be null")
    @NotBlank(message = "Content can not be blank")
    private String content;

}
