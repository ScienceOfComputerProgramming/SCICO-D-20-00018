package com.gamaza.rest4cep.design.mapper;

import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternPostDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternPutDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternWithListDto;
import com.gamaza.rest4cep.design.model.EventPattern;
import org.mapstruct.*;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.MAPPER_COMPONENT_MODEL;

/**
 * Event Pattern Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventPatternMapper {

    // Map to model
    EventPattern mapToEntity(EventPatternPostDto eventPatternPostDto);

    // Update operations
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventPattern mapToEntity(@MappingTarget EventPattern target, EventPatternPutDto source);

    // Map to DTO
    EventPatternDto mapToDto(EventPattern eventPattern);

    // Map to DTO with list
    EventPatternWithListDto mapToDtoWithList(EventPattern eventPattern);

}
