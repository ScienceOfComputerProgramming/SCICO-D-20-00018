package com.gamaza.rest4cep.mysql.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.config.exception.UpdateException;
import com.gamaza.rest4cep.mysql.dao.EventPatternDao;
import com.gamaza.rest4cep.mysql.dao.EventTypeDao;
import com.gamaza.rest4cep.mysql.dto.EventTypeDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePostDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePutDto;
import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;
import com.gamaza.rest4cep.mysql.mapper.EventTypeMapper;
import com.gamaza.rest4cep.mysql.model.EventType;
import com.gamaza.rest4cep.mysql.service.EventTypeService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Event Type Service Implementation
 */
@Service
public class EventTypeServiceImpl implements EventTypeService {

    // Private variables for injection
    private final EventTypeDao eventTypeDao;
    private final EventTypeMapper eventTypeMapper;
    private final EventPatternDao eventPatternDao;

    /**
     * Constructor injection
     */
    public EventTypeServiceImpl(EventTypeDao eventTypeDao, EventTypeMapper eventTypeMapper, EventPatternDao eventPatternDao) {
        this.eventTypeDao = eventTypeDao;
        this.eventTypeMapper = eventTypeMapper;
        this.eventPatternDao = eventPatternDao;
    }

    @Override
    public EventTypeWithListDto create(EventTypePostDto eventTypePostDto) {
        EventType createdEventType = eventTypeDao.save(
                eventTypeMapper.mapToEntity(eventTypePostDto)
        );
        return eventTypeMapper.mapToDtoWithList(createdEventType);
    }

    @Override
    public List<EventTypeDto> readAll() {
        return Lists.newArrayList(eventTypeDao.findAll())
                .stream()
                .map(eventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventTypeDto> readAllByIsEnabled(boolean status) {
        return Lists.newArrayList(eventTypeDao.findAllByEnabled(status)).stream()
                .map(eventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventTypeWithListDto readOneById(Integer id) {
        Optional<EventType> retrievedEventType = eventTypeDao.findById(id);
        if (retrievedEventType.isPresent())
            return eventTypeMapper.mapToDtoWithList(retrievedEventType.get());
        else {
            String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "id=" + id);
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public EventTypeWithListDto readOneByName(String name) {
        Optional<EventType> retrievedEventType = eventTypeDao.findByName(name);
        if (retrievedEventType.isPresent())
            return eventTypeMapper.mapToDtoWithList(retrievedEventType.get());
        else {
            String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "name=" + name);
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public EventTypeWithListDto readOneByChannelId(Integer channelId) {
        Optional<EventType> retrievedEventType = eventTypeDao.findByChannel(channelId);
        if (retrievedEventType.isPresent())
            return eventTypeMapper.mapToDtoWithList(retrievedEventType.get());
        else {
            String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "channelId=" + channelId);
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public void update(Integer id, EventTypePutDto eventTypePutDto) {
        // Search the eventType in database for update only if exists
        eventTypeDao.findById(id).ifPresentOrElse(
                eventType -> {
                    if (!eventType.isEnabled()) {
                        EventType mappedEventType = eventTypeMapper.mapToEntity(eventType, eventTypePutDto);
                        eventTypeDao.save(mappedEventType);
                    } else {
                        String exceptionMessage = String.format(MESSAGE_UPDATE_EVENT_TYPE_EXCEPTION, OBJECT_EVENT_TYPE, "id=" + id);
                        throw new UpdateException(exceptionMessage);
                    }
                },
                () -> {
                    String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "id=" + id);
                    throw new NotFoundException(exceptionMessage);
                }
        );
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        // Retrieve the Event Type from database (if exists)
        EventType retrievedEventype = eventTypeDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "id=" + id);
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Update status only if the Event Type exists
        if (!status) {
            if (retrievedEventype.isEnabled()) {
                retrievedEventype.getEventPatterns().forEach(
                        eventPattern -> eventPatternDao.updateStatus(eventPattern.getId(), false)
                );
            } else {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_EVENT_TYPE_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        "id=" + id,
                        OPERATION_WORD_NOT
                );
                throw new UpdateException(exceptionMessage);
            }
        } else {
            if (retrievedEventype.isEnabled()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_EVENT_TYPE_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        "id=" + id,
                        OPERATION_WORD_ALREADY
                );
                throw new UpdateException(exceptionMessage);
            }
        }
        // Update Event Type status
        retrievedEventype.setEnabled(status);
        eventTypeDao.save(retrievedEventype);
    }

    @Override
    public void delete(Integer id) {
        // Delete the Event Type only if exists
        eventTypeDao.findById(id).ifPresentOrElse(
                eventType -> {
                    // Undeploy all linked Event Patterns
                    eventType.getEventPatterns().forEach(eventPattern ->
                            eventPatternDao.updateStatus(eventPattern.getId(), false)
                    );
                    // Delete the Event Type
                    eventTypeDao.deleteById(id);
                },
                () -> {
                    String exceptionMessage = String.format(MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE, "id=" + id);
                    throw new NotFoundException(exceptionMessage);
                }
        );
    }

}
