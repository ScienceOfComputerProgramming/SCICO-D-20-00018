package com.gamaza.rest4cep.mongo.mapper;

import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.mongo.model.ComplexEventDetectedMongo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigurationConstants.MAPPER_COMPONENT_MODEL;

/**
 * Complex Events Detected Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplexEventsDetectedMongoMapper {

    // Map to entity
    ComplexEventDetectedMongo mapToEntity(ComplexEventDetectedPostDto complexEventDetectedPostDto);

    // Map to DTO
    ComplexEventDetectedMongoDto mapToDto(ComplexEventDetectedMongo complexEventDetectedMongo);

}
