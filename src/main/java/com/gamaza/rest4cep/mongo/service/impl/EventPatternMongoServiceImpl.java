package com.gamaza.rest4cep.mongo.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.mongo.dao.EventPatternMongoDao;
import com.gamaza.rest4cep.mongo.dto.EventPatternMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventPatternMongoPostDto;
import com.gamaza.rest4cep.mongo.mapper.EventPatternMongoMapper;
import com.gamaza.rest4cep.mongo.model.EventPatternMongo;
import com.gamaza.rest4cep.mongo.service.EventPatternMongoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Event Pattern Service Implementation
 */
@Service
public class EventPatternMongoServiceImpl implements EventPatternMongoService {

    // Private variables for injection
    private final EventPatternMongoDao eventPatternMongoDao;
    private final EventPatternMongoMapper eventPatternMongoMapper;

    /**
     * Constructor Injection
     */
    public EventPatternMongoServiceImpl(EventPatternMongoDao eventPatternMongoDao, EventPatternMongoMapper eventPatternMongoMapper) {
        this.eventPatternMongoDao = eventPatternMongoDao;
        this.eventPatternMongoMapper = eventPatternMongoMapper;
    }

    @Override
    public EventPatternMongoDto create(EventPatternMongoPostDto eventPatternPostDto) {
        EventPatternMongo createdEventPatternMongo = eventPatternMongoDao.save(
                eventPatternMongoMapper.mapToEntity(eventPatternPostDto)
        );
        return eventPatternMongoMapper.mapToDto(createdEventPatternMongo);
    }

    @Override
    public List<EventPatternMongoDto> readAll() {
        return Lists.newArrayList(eventPatternMongoDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(eventPatternMongoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventPatternMongoDto readOneById(String id) {
        EventPatternMongo retrievedEventPatternMongo = eventPatternMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return eventPatternMongoMapper.mapToDto(retrievedEventPatternMongo);
    }

    @Override
    public List<EventPatternMongoDto> readLast5() {
        return Lists.newArrayList(eventPatternMongoDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(eventPatternMongoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        eventPatternMongoDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        EventPatternMongo retrievedEventPatternMongo = eventPatternMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        eventPatternMongoDao.delete(retrievedEventPatternMongo);
    }

}
