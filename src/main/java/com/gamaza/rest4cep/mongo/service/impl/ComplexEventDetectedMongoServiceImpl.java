package com.gamaza.rest4cep.mongo.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.mongo.dao.ComplexEventMongoDao;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.mongo.mapper.ComplexEventsDetectedMongoMapper;
import com.gamaza.rest4cep.mongo.model.ComplexEventDetectedMongo;
import com.gamaza.rest4cep.mongo.service.ComplexEventDetectedMongoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.MESSAGE_NOT_FOUND_EXCEPTION;
import static com.gamaza.rest4cep.config.constant.ExceptionConstants.OBJECT_COMPLEX_EVENT_DETECTED;

/**
 * Complex Events Detected Service Implementation
 */
@Service
public class ComplexEventDetectedMongoServiceImpl implements ComplexEventDetectedMongoService {

    // Private variables for injection
    private final ComplexEventMongoDao complexEventMongoDao;
    private final ComplexEventsDetectedMongoMapper complexEventsDetectedMapper;

    /**
     * Constructor injection
     */
    public ComplexEventDetectedMongoServiceImpl(ComplexEventMongoDao complexEventMongoDao, ComplexEventsDetectedMongoMapper complexEventsDetectedMongoMapper) {
        this.complexEventMongoDao = complexEventMongoDao;
        this.complexEventsDetectedMapper = complexEventsDetectedMongoMapper;
    }

    @Override
    public ComplexEventDetectedMongoDto create(ComplexEventDetectedPostDto complexEventDetectedPostDto) {
        ComplexEventDetectedMongo retrievedComplexEventDetectedMongo = complexEventMongoDao.save(
                complexEventsDetectedMapper.mapToEntity(complexEventDetectedPostDto)
        );
        return complexEventsDetectedMapper.mapToDto(retrievedComplexEventDetectedMongo);
    }

    @Override
    public List<ComplexEventDetectedMongoDto> readAll() {
        return Lists.newArrayList(complexEventMongoDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(complexEventsDetectedMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ComplexEventDetectedMongoDto readOneById(String id) {
        ComplexEventDetectedMongo retrievedComplexEventDetectedMongo = complexEventMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_COMPLEX_EVENT_DETECTED, "id=" + id);
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return complexEventsDetectedMapper.mapToDto(retrievedComplexEventDetectedMongo);
    }

    @Override
    public List<ComplexEventDetectedMongoDto> readLast5() {
        return Lists.newArrayList(complexEventMongoDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(complexEventsDetectedMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        complexEventMongoDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        ComplexEventDetectedMongo retrievedComplexEventDetectedMongo = complexEventMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_COMPLEX_EVENT_DETECTED, "id=" + id);
                    throw new NotFoundException(exceptionMessage);
                }
        );
        complexEventMongoDao.delete(retrievedComplexEventDetectedMongo);
    }
}
