package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Event Types DTO (With EPL Event Pattern List)
 */
@Getter @Setter @ToString
public class EventTypeWithListDto {

    private Integer id;

    @NotNull
    private Integer channel;

    @NotNull
    private String name;

    @NotNull
    private String description = "No description";

    private boolean isEnabled = false;

    @NotNull
    private List<EplEventPatternDto> eplEventPatterns = new ArrayList<>();

}

