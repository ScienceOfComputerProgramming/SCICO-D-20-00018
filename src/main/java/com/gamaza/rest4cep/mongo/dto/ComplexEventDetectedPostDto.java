package com.gamaza.rest4cep.mongo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Complex Events Detected Data Transfer Object for POST operations
 */
@Getter @Setter
public class ComplexEventDetectedPostDto implements Serializable {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 596852851162019458L;

    @NotNull(message = "Detected by can not be null")
    @NotBlank(message = "Detected by can not be blank")
    private String detectedBy;

    @NotNull(message = "Detected event can not be null")
    @NotBlank(message = "Detected event can not be blank")
    private String detectedEvent;

}
