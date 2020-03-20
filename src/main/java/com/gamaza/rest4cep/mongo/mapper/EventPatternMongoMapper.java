package com.gamaza.rest4cep.mongo.mapper;

import com.gamaza.rest4cep.mongo.dto.EventPatternMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventPatternMongoPostDto;
import com.gamaza.rest4cep.mongo.model.EventPatternMongo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigurationConstants.MAPPER_COMPONENT_MODEL;

/**
 * Event Pattern Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventPatternMongoMapper {

    // Map to entity
    EventPatternMongo mapToEntity(EventPatternMongoPostDto eventPatternMongoPostDto);

    // Map to DTO
    EventPatternMongoDto mapToDto(EventPatternMongo eventPattern);

}
