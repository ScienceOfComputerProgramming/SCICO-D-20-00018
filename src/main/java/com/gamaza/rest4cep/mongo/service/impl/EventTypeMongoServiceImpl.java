package com.gamaza.rest4cep.mongo.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.mongo.dao.EventTypeMongoDao;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoPostDto;
import com.gamaza.rest4cep.mongo.mapper.EventTypeMongoMapper;
import com.gamaza.rest4cep.mongo.model.EventTypeMongo;
import com.gamaza.rest4cep.mongo.service.EventTypeMongoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Event Type Service Implementation
 */
@Service
public class EventTypeMongoServiceImpl implements EventTypeMongoService {

    // Private variables for injection
    private final EventTypeMongoDao eventTypeMongoDao;
    private final EventTypeMongoMapper eventTypeMongoMapper;

    /**
     * Constructor injection
     */
    public EventTypeMongoServiceImpl(EventTypeMongoDao eventTypeDao, EventTypeMongoMapper eventTypeMongoMapper) {
        this.eventTypeMongoDao = eventTypeDao;
        this.eventTypeMongoMapper = eventTypeMongoMapper;
    }

    @Override
    public EventTypeMongoDto create(EventTypeMongoPostDto eventTypePostDto) {
        EventTypeMongo createdEventTypeMongo = eventTypeMongoDao.save(
                eventTypeMongoMapper.mapToEntity(eventTypePostDto)
        );
        return eventTypeMongoMapper.mapToDto(createdEventTypeMongo);
    }

    @Override
    public List<EventTypeMongoDto> readAll() {
        return Lists.newArrayList(eventTypeMongoDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(eventTypeMongoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventTypeMongoDto readOneById(String id) {
        EventTypeMongo retrievedEventTypeMongo = eventTypeMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return eventTypeMongoMapper.mapToDto(retrievedEventTypeMongo);
    }

    @Override
    public List<EventTypeMongoDto> readLast5() {
        return Lists.newArrayList(eventTypeMongoDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(eventTypeMongoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        eventTypeMongoDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        EventTypeMongo retrievedEventTypeMongo = eventTypeMongoDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        eventTypeMongoDao.delete(retrievedEventTypeMongo);
    }

}
