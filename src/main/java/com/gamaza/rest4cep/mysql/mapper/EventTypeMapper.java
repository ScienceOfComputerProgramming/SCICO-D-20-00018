package com.gamaza.rest4cep.mysql.mapper;

import com.gamaza.rest4cep.mysql.dto.EventTypeDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePostDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePutDto;
import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;
import com.gamaza.rest4cep.mysql.model.EventType;
import com.google.common.base.Strings;
import org.mapstruct.*;

import static com.gamaza.rest4cep.config.constant.ConfigurationConstants.MAPPER_COMPONENT_MODEL;
import static com.gamaza.rest4cep.config.constant.EntityConstants.DEFAULT_DESCRIPTION;

/**
 * Event Type Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventTypeMapper {

    // Map to model
    @Mapping(source = "description", target = "description", qualifiedByName = "descriptionMapping")
    EventType mapToEntity(EventTypePostDto eventTypePostDto);

    // Update operations
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventType mapToEntity(@MappingTarget EventType target, EventTypePutDto source);

    // Map to DTO
    EventTypeDto mapToDto(EventType eventType);

    // Map to DTO with list
    EventTypeWithListDto mapToDtoWithList(EventType eventType);

    @Named(value = "descriptionMapping")
    default String descriptionMapping(String description) {
        return !Strings.isNullOrEmpty(description) ? description : DEFAULT_DESCRIPTION;
    }

}
