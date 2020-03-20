package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Event Type Data Transfer Object for POST operations
 */
@Getter @Setter
public class EventTypePostDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = -4896466597863345553L;

    @NotNull(message = "Channel for the Event Type can not be null")
    private Integer channel;

    @NotNull(message = "Name for the Event Type can not be null")
    @NotBlank(message = "Name for the Event Type can not be blank")
    private String name;

    private String description;
    private boolean enabled;

}
