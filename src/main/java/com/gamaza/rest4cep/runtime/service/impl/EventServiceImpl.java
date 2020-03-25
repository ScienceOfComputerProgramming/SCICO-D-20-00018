package com.gamaza.rest4cep.runtime.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.runtime.dao.EventDao;
import com.gamaza.rest4cep.runtime.dto.event.EventDto;
import com.gamaza.rest4cep.runtime.dto.event.EventPostDto;
import com.gamaza.rest4cep.runtime.mapper.EventMapper;
import com.gamaza.rest4cep.runtime.model.Event;
import com.gamaza.rest4cep.runtime.service.EventService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Event Service Implementation
 */
@Service
public class EventServiceImpl implements EventService {

    // Private variables for injection
    private final EventDao eventDao;
    private final EventMapper eventMapper;

    /**
     * Constructor injection
     */
    public EventServiceImpl(EventDao eventDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDto create(EventPostDto eventPostDto) {
        Event createdEvent = eventDao.save(
                eventMapper.mapToEntity(eventPostDto)
        );
        return eventMapper.mapToDto(createdEvent);
    }

    @Override
    public List<EventDto> readAll() {
        return Lists.newArrayList(eventDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(eventMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto readOneById(String id) {
        Event retrievedEvent = eventDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return eventMapper.mapToDto(retrievedEvent);
    }

    @Override
    public List<EventDto> readLast5() {
        return Lists.newArrayList(eventDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(eventMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        eventDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        Event retrievedEvent = eventDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        eventDao.delete(retrievedEvent);
    }

}
