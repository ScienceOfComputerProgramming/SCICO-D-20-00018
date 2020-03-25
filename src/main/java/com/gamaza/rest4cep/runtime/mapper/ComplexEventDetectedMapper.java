package com.gamaza.rest4cep.runtime.mapper;

import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedDto;
import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.runtime.model.ComplexEventDetected;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.MAPPER_COMPONENT_MODEL;

/**
 * Complex Events Detected Mapper
 */
@Mapper(componentModel = MAPPER_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplexEventDetectedMapper {

    // Map to entity
    ComplexEventDetected mapToEntity(ComplexEventDetectedPostDto complexEventDetectedPostDto);

    // Map to DTO
    ComplexEventDetectedDto mapToDto(ComplexEventDetected complexEventDetected);

}
