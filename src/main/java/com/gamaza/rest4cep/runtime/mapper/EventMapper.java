package com.gamaza.rest4cep.runtime.mapper;

import com.gamaza.rest4cep.runtime.dto.event.EventDto;
import com.gamaza.rest4cep.runtime.dto.event.EventPostDto;
import com.gamaza.rest4cep.runtime.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.MAPPER_COMPONENT_MODEL;

/**
 * Event Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    // Map to entity
    Event mapToEntity(EventPostDto eventPostDto);

    // Map to DTO
    EventDto mapToDto(Event event);

}
