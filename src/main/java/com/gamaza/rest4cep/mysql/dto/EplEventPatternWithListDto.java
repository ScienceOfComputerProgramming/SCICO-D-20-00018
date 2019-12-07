package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * EPL Event Patterns DTO (With Event Types List)
 */
@Getter @Setter @ToString
public class EplEventPatternWithListDto {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    private boolean isDeployed = false;

    private boolean isInEsper = false;

    @NotNull
    private List<EventTypeDto> eventTypes = new ArrayList<>();

}