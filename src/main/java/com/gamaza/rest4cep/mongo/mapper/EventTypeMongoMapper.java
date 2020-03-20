package com.gamaza.rest4cep.mongo.mapper;

import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoPostDto;
import com.gamaza.rest4cep.mongo.model.EventTypeMongo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigurationConstants.MAPPER_COMPONENT_MODEL;

/**
 * Event Type Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventTypeMongoMapper {

    // Map to entity
    EventTypeMongo mapToEntity(EventTypeMongoPostDto eventTypePostDto);

    // Map to DTO
    EventTypeMongoDto mapToDto(EventTypeMongo eventType);

}
