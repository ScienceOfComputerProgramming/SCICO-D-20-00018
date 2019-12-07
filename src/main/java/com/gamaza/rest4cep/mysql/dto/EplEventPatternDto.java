package com.gamaza.rest4cep.mysql.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * EPL Event Patterns DTO
 */
@Getter @Setter @ToString
public class EplEventPatternDto {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    private boolean isDeployed = false;

    private boolean isInEsper = false;

}
