package com.gamaza.rest4cep.design.service.impl;

import com.gamaza.rest4cep.config.exception.AlreadyExistsException;
import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.config.exception.UpdateException;
import com.gamaza.rest4cep.design.dao.EventPatternDao;
import com.gamaza.rest4cep.design.dao.EventTypeDao;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypeDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePostDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePutDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypeWithListDto;
import com.gamaza.rest4cep.design.mapper.EventTypeMapper;
import com.gamaza.rest4cep.design.model.EventType;
import com.gamaza.rest4cep.design.service.EventTypeService;
import com.google.common.collect.Lists;
import org.springframework.dao.DataIntegrityViolationException;
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
        EventType createdEventType;
        try {
            createdEventType = eventTypeDao.save(
                    eventTypeMapper.mapToEntity(eventTypePostDto)
            );
        } catch (DataIntegrityViolationException e) {
            String exceptionMessage = String.format(
                    MESSAGE_ALREADY_EXISTS_EXCEPTION,
                    OBJECT_EVENT_TYPE,
                    String.format(FORMAT_NAME_TEXT, eventTypePostDto.getName())
            );
            throw new AlreadyExistsException(exceptionMessage);
        }
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
    public List<EventTypeDto> readAllByIsReadyToDeploy(boolean status) {
        return Lists.newArrayList(eventTypeDao.findAllByReadyToDeploy(status)).stream()
                .map(eventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventTypeDto> readAllByIsDeployed(boolean status) {
        return Lists.newArrayList(eventTypeDao.findAllByDeployed(status)).stream()
                .map(eventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventTypeWithListDto readOneById(Integer id) {
        Optional<EventType> retrievedEventType = eventTypeDao.findById(id);
        if (retrievedEventType.isPresent())
            return eventTypeMapper.mapToDtoWithList(retrievedEventType.get());
        else {
            String exceptionMessage = String.format(
                    MESSAGE_NOT_FOUND_EXCEPTION,
                    OBJECT_EVENT_TYPE,
                    String.format(FORMAT_ID_TEXT, id)
            );
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public EventTypeWithListDto readOneByName(String name) {
        Optional<EventType> retrievedEventType = eventTypeDao.findByName(name);
        if (retrievedEventType.isPresent())
            return eventTypeMapper.mapToDtoWithList(retrievedEventType.get());
        else {
            String exceptionMessage = String.format(
                    MESSAGE_NOT_FOUND_EXCEPTION,
                    OBJECT_EVENT_TYPE,
                    String.format(FORMAT_NAME_TEXT, name)
            );
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public void update(Integer id, EventTypePutDto eventTypePutDto) {
        try {
            // Search the eventType in database for update only if exists
            eventTypeDao.findById(id).ifPresentOrElse(
                    eventType -> {
                        if (!eventType.isReadyToDeploy()) {
                            EventType mappedEventType = eventTypeMapper.mapToEntity(eventType, eventTypePutDto);
                            eventTypeDao.save(mappedEventType);
                        } else {
                            String exceptionMessage = String.format(
                                    MESSAGE_UPDATE_EXCEPTION,
                                    OBJECT_EVENT_TYPE,
                                    String.format(FORMAT_ID_TEXT, id),
                                    true
                            );
                            throw new UpdateException(exceptionMessage);
                        }
                    },
                    () -> {
                        String exceptionMessage = String.format(
                                MESSAGE_NOT_FOUND_EXCEPTION,
                                OBJECT_EVENT_TYPE,
                                String.format(FORMAT_ID_TEXT, id)
                        );
                        throw new NotFoundException(exceptionMessage);
                    }
            );
        } catch (DataIntegrityViolationException e) {
            String exceptionMessage = String.format(
                    MESSAGE_ALREADY_EXISTS_EXCEPTION,
                    OBJECT_EVENT_TYPE,
                    String.format(FORMAT_NAME_TEXT, eventTypePutDto.getName())
            );
            throw new AlreadyExistsException(exceptionMessage);
        }
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        // Retrieve the Event Type from database (if exists)
        EventType retrievedEventType = eventTypeDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION, OBJECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Update status only if the Event Type exists
        if (!status) {
            if (retrievedEventType.isReadyToDeploy()) {
                retrievedEventType.getEventPatterns().forEach(
                        eventPattern -> eventPatternDao.updateStatus(eventPattern.getId(), false)
                );
                retrievedEventType.setDeployed(false);
            } else {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_INCONSISTENT_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_NOT
                );
                throw new UpdateException(exceptionMessage);
            }
        } else {
            if (retrievedEventType.isReadyToDeploy()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_INCONSISTENT_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_ALREADY
                );
                throw new UpdateException(exceptionMessage);
            }
        }
        // Update Event Type status
        retrievedEventType.setReadyToDeploy(status);
        eventTypeDao.save(retrievedEventType);
    }

    @Override
    public void updateDeployingStatus(Integer id, boolean status) {
        // Retrieve the Event Type from database (if exists)
        EventType retrievedEventType = eventTypeDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Change the status according to the received value
        if (status) {
            // If the Event Type is deployed, it can not be set at the same state
            if (retrievedEventType.isDeployed()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_DEPLOYED_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_ALREADY
                );
                throw new UpdateException(exceptionMessage);
            }
            // If the Event Type is not ready to deploy, it can not be deployed
            if (!retrievedEventType.isReadyToDeploy()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, id),
                        false
                );
                throw new UpdateException(exceptionMessage);
            }
        } else {
            // If the Event Type is not deployed, it can not be set at the same state
            if (!retrievedEventType.isDeployed()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_DEPLOYED_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_NOT
                );
                throw new UpdateException(exceptionMessage);
            }
            else {
                retrievedEventType.getEventPatterns().forEach(
                        eventPattern -> eventPatternDao.updateStatus(eventPattern.getId(), false)
                );
            }
        }
        retrievedEventType.setDeployed(status);
        eventTypeDao.save(retrievedEventType);
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
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
    }

}
