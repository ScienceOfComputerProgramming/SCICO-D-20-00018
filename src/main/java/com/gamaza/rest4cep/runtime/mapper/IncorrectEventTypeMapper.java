package com.gamaza.rest4cep.runtime.mapper;

import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypeDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypePostDto;
import com.gamaza.rest4cep.runtime.model.IncorrectEventType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.MAPPER_COMPONENT_MODEL;

/**
 * Incorrect Event Type Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncorrectEventTypeMapper {

    // Map to entity
    IncorrectEventType mapToEntity(IncorrectEventTypePostDto incorrectEventTypePostDto);

    // Map to DTO
    IncorrectEventTypeDto mapToDto(IncorrectEventType incorrectEventType);

}
