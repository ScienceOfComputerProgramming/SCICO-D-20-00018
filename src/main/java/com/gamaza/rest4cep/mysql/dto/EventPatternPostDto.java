package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Event Pattern Data Transfer Object for POST operations
 */
@Getter @Setter
public class EventPatternPostDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -7435011067631035720L;

    @NotNull(message = "Name for the Event Type can not be null")
    @NotBlank(message = "Name for the Event Type can not be blank")
    private String name;

    @NotNull(message = "Content for the Event Type can not be null")
    @NotBlank(message = "Content for the Event Type can not be blank")
    private String content;

    private boolean readyToDeploy;

}
