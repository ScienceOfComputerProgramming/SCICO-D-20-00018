package com.gamaza.rest4cep.runtime.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.runtime.dao.ComplexEventDetectedDao;
import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedDto;
import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.runtime.mapper.ComplexEventDetectedMapper;
import com.gamaza.rest4cep.runtime.model.ComplexEventDetected;
import com.gamaza.rest4cep.runtime.service.ComplexEventDetectedService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Complex Events Detected Service Implementation
 */
@Service
public class ComplexEventDetectedServiceImpl implements ComplexEventDetectedService {

    // Private variables for injection
    private final ComplexEventDetectedDao complexEventDetectedDao;
    private final ComplexEventDetectedMapper complexEventsDetectedMapper;

    /**
     * Constructor injection
     */
    public ComplexEventDetectedServiceImpl(ComplexEventDetectedDao complexEventDetectedDao, ComplexEventDetectedMapper complexEventDetectedMapper) {
        this.complexEventDetectedDao = complexEventDetectedDao;
        this.complexEventsDetectedMapper = complexEventDetectedMapper;
    }

    @Override
    public ComplexEventDetectedDto create(ComplexEventDetectedPostDto complexEventDetectedPostDto) {
        ComplexEventDetected retrievedComplexEventDetected = complexEventDetectedDao.save(
                complexEventsDetectedMapper.mapToEntity(complexEventDetectedPostDto)
        );
        return complexEventsDetectedMapper.mapToDto(retrievedComplexEventDetected);
    }

    @Override
    public List<ComplexEventDetectedDto> readAll() {
        return Lists.newArrayList(complexEventDetectedDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(complexEventsDetectedMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ComplexEventDetectedDto readOneById(String id) {
        ComplexEventDetected retrievedComplexEventDetected = complexEventDetectedDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_COMPLEX_EVENT_DETECTED,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return complexEventsDetectedMapper.mapToDto(retrievedComplexEventDetected);
    }

    @Override
    public List<ComplexEventDetectedDto> readLast5() {
        return Lists.newArrayList(complexEventDetectedDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(complexEventsDetectedMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        complexEventDetectedDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        ComplexEventDetected retrievedComplexEventDetected = complexEventDetectedDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_COMPLEX_EVENT_DETECTED,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        complexEventDetectedDao.delete(retrievedComplexEventDetected);
    }
}
