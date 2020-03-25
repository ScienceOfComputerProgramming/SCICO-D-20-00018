package com.gamaza.rest4cep.runtime.mapper;

import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternPostDto;
import com.gamaza.rest4cep.runtime.model.IncorrectEventPattern;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.MAPPER_COMPONENT_MODEL;

/**
 * Incorrect Event Pattern Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncorrectEventPatternMapper {

    // Map to entity
    IncorrectEventPattern mapToEntity(IncorrectEventPatternPostDto incorrectEventPatternPostDto);

    // Map to DTO
    IncorrectEventPatternDto mapToDto(IncorrectEventPattern eventPattern);

}
