package com.gamaza.rest4cep.mysql.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Event Types DTO
 */
@Getter @Setter @ToString
public class EventTypeDto {

    private Integer id;

    @NotNull
    private Integer channel;

    @NotNull
    private String name;

    @NotNull
    private String description = "No description";

    private boolean isEnabled = false;

}
