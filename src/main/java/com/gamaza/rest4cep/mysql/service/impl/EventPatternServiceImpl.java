package com.gamaza.rest4cep.mysql.service.impl;

import com.gamaza.rest4cep.config.exception.AlreadyExistsException;
import com.gamaza.rest4cep.config.exception.LinkException;
import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.config.exception.UpdateException;
import com.gamaza.rest4cep.mysql.dao.EventPatternDao;
import com.gamaza.rest4cep.mysql.dao.EventTypeDao;
import com.gamaza.rest4cep.mysql.dto.EventPatternDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternPostDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternPutDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternWithListDto;
import com.gamaza.rest4cep.mysql.mapper.EventPatternMapper;
import com.gamaza.rest4cep.mysql.model.EventPattern;
import com.gamaza.rest4cep.mysql.model.EventType;
import com.gamaza.rest4cep.mysql.service.EventPatternService;
import com.google.common.collect.Lists;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Event Pattern Service Implementation
 */
@Service
public class EventPatternServiceImpl implements EventPatternService {

    // Private variables for injection
    private final EventPatternDao eventPatternDao;
    private final EventPatternMapper eventPatternMapper;
    private final EventTypeDao eventTypeDao;

    /**
     * Constructor injection
     */
    public EventPatternServiceImpl(EventPatternDao eventPatternDao, EventPatternMapper eventPatternMapper, EventTypeDao eventTypeDao) {
        this.eventPatternDao = eventPatternDao;
        this.eventPatternMapper = eventPatternMapper;
        this.eventTypeDao = eventTypeDao;
    }

    @Override
    public EventPatternWithListDto create(EventPatternPostDto eventPatternPostDto) {
        EventPattern createdEventPattern;
        try {
            createdEventPattern = eventPatternDao.save(
                    eventPatternMapper.mapToEntity(eventPatternPostDto)
            );
        } catch (DataIntegrityViolationException e) {
            String exceptionMessage = String.format(
                    MESSAGE_ALREADY_EXISTS_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_NAME_TEXT, eventPatternPostDto.getName())
            );
            throw new AlreadyExistsException(exceptionMessage);
        }
        return eventPatternMapper.mapToDtoWithList(createdEventPattern);
    }

    @Override
    public List<EventPatternDto> readAll() {
        return Lists.newArrayList(eventPatternDao.findAll()).stream()
                .map(eventPatternMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventPatternDto> readAllByIsReadyToDeploy(boolean status) {
        return Lists.newArrayList(eventPatternDao.findAllByReadyToDeploy(status)).stream()
                .map(eventPatternMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventPatternDto> readAllByIsDeployed(boolean status) {
        return Lists.newArrayList(eventPatternDao.findAllByDeployed(status)).stream()
                .map(eventPatternMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventPatternWithListDto readOneById(Integer id) {
        Optional<EventPattern> retrievedEventPattern = eventPatternDao.findById(id);
        if (retrievedEventPattern.isPresent())
            return eventPatternMapper.mapToDtoWithList(retrievedEventPattern.get());
        else {
            String exceptionMessage = String.format(
                    MESSAGE_NOT_FOUND_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, id)
            );
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public EventPatternWithListDto readOneByName(String name) {
        Optional<EventPattern> retrievedEventPattern = eventPatternDao.findByName(name);
        if (retrievedEventPattern.isPresent())
            return eventPatternMapper.mapToDtoWithList(retrievedEventPattern.get());
        else {
            String exceptionMessage = String.format(
                    MESSAGE_NOT_FOUND_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_NAME_TEXT, name)
            );
            throw new NotFoundException(exceptionMessage);
        }
    }

    @Override
    public void update(Integer id, EventPatternPutDto eventPatternPutDto) {
        // Retrieve the Event Pattern from database (if exists)
        EventPattern retrievedEventPattern = eventPatternDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Update the Event Pattern only if it is not deployed
        if (!retrievedEventPattern.isReadyToDeploy()) {
            EventPattern mappedEventPattern = eventPatternMapper.mapToEntity(retrievedEventPattern, eventPatternPutDto);
            eventPatternDao.save(mappedEventPattern);
        } else {
            String exceptionMessage = String.format(
                    MESSAGE_UPDATE_EVENT_PATTERN_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, id)
            );
            throw new UpdateException(exceptionMessage);
        }
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        // Retrieve the Event Pattern from database (if exists)
        EventPattern retrievedEventPattern = eventPatternDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Change the status according to the received value
        if (status) {
            // If the Event Pattern is ready to deploy, it can not be set at the same state
            if (retrievedEventPattern.isReadyToDeploy()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_INCONSISTENT_EVENT_PATTERN_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_ALREADY
                );
                throw new UpdateException(exceptionMessage);
            }
            checkLinkedEventTypesStatus(retrievedEventPattern);
        } else {
            // If the Event Pattern is not ready to deploy, it can not be set at the same state
            if (!retrievedEventPattern.isReadyToDeploy()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_INCONSISTENT_EVENT_PATTERN_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_NOT
                );
                throw new UpdateException(exceptionMessage);
            }
        }
        eventPatternDao.updateStatus(id, status);
    }

    @Override
    public void updateDeployingStatus(Integer id, boolean status) {
        // Retrieve the Event Pattern from database (if exists)
        EventPattern retrievedEventPattern = eventPatternDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        // Change the status according to the received value
        if (status) {
            // If the Event Pattern is deployed, it can not be set at the same state
            if (retrievedEventPattern.isDeployed()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_DEPLOYED_EVENT_PATTERN_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_ALREADY
                );
                throw new UpdateException(exceptionMessage);
            }
        } else {
            // If the Event Pattern is not deployed, it can not be set at the same state
            if (!retrievedEventPattern.isDeployed()) {
                String exceptionMessage = String.format(
                        MESSAGE_UPDATE_STATUS_DEPLOYED_EVENT_PATTERN_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, id),
                        OPERATION_WORD_NOT
                );
                throw new UpdateException(exceptionMessage);
            }
        }
        retrievedEventPattern.setDeployed(status);
        eventPatternDao.save(retrievedEventPattern);
    }

    /**
     * Private function to check if the received Event Pattern is correct with his related Event Types
     *
     * @param retrievedEventPattern The retrieved pattern from database
     */
    private void checkLinkedEventTypesStatus(EventPattern retrievedEventPattern) {
        // Get the related Event Types of the Event Pattern
        List<EventType> eventTypes = retrievedEventPattern.getEventTypes();
        Integer eventPatternId = retrievedEventPattern.getId();
        // If the Event Pattern has not linked Event Types, it cannot be deployed
        if (eventTypes.isEmpty()) {
            String exceptionMessage = String.format(
                    MESSAGE_UPDATE_STATUS_EVENT_PATTERN_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, eventPatternId),
                    String.format(COMMENTS_UPDATE_STATUS_EMPTY_EVENT_TYPES, OBJECT_EVENT_TYPE)
            );
            throw new UpdateException(exceptionMessage);
        }
        // If the Event Pattern has disabled Event Types, it cannot be deployed
        List<String> notEnabledEventTypes = eventTypes.stream()
                .filter(currentEventType -> !currentEventType.isEnabled())
                .map(EventType::getName)
                .collect(Collectors.toList());
        if (!notEnabledEventTypes.isEmpty()) {
            String exceptionMessage = String.format(
                    MESSAGE_UPDATE_STATUS_EVENT_PATTERN_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, eventPatternId),
                    String.format(COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_EVENT_TYPE, notEnabledEventTypes, OBJECT_EVENT_TYPE)
            );
            throw new UpdateException(exceptionMessage);
        }
        // Check that the Event Pattern content contains his Event Types linked written in the queries
        List<String> notContainedEventTypes = eventTypes.stream()
                .map(EventType::getName)
                .filter(currentEventType -> !retrievedEventPattern.getContent().contains(currentEventType))
                .collect(Collectors.toList());
        if (!notContainedEventTypes.isEmpty()) {
            String exceptionMessage = String.format(
                    MESSAGE_UPDATE_STATUS_EVENT_PATTERN_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, eventPatternId),
                    String.format(COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_PATTERN_CONTENT, notContainedEventTypes, OBJECT_EVENT_TYPE)
            );
            throw new UpdateException(exceptionMessage);
        }
    }

    @Override
    public void setPatternLink(Integer eventPatternId, Integer eventTypeId, boolean linkStatus) {
        // Retrieve the Event Type and Event Pattern objects from database (if they exist)
        EventType retrievedEventType = eventTypeDao.findById(eventTypeId).orElseThrow(
                () -> new NotFoundException(String.format(
                        MESSAGE_NOT_FOUND_EXCEPTION,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, eventTypeId)
                ))
        );
        EventPattern retrievedEventPattern = eventPatternDao.findById(eventPatternId).orElseThrow(
                () -> new NotFoundException(String.format(
                        MESSAGE_NOT_FOUND_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, eventPatternId))
                )
        );
        // Link or unlink according to the received status
        if (linkStatus) {
            // Check that the Event Pattern and the Event Pattern received are not linked
            if (!retrievedEventPattern.getEventTypes().contains(retrievedEventType)) {
                retrievedEventPattern.getEventTypes().add(retrievedEventType);
                retrievedEventType.getEventPatterns().add(retrievedEventPattern);
            } else {
                // Throw an exception in other case
                String exceptionMessage = String.format(
                        MESSAGE_LINK_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, eventPatternId),
                        OPERATION_LINK,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, eventTypeId),
                        OPERATION_WORD_ALREADY
                );
                throw new LinkException(exceptionMessage);
            }
        } else {
            // Check that the Event Pattern and the Event Pattern received are linked
            if (retrievedEventPattern.getEventTypes().contains(retrievedEventType)) {
                retrievedEventPattern.getEventTypes().remove(retrievedEventType);
                retrievedEventType.getEventPatterns().remove(retrievedEventPattern);
            } else {
                // Throw an exception in other case
                String exceptionMessage = String.format(
                        MESSAGE_LINK_EXCEPTION,
                        OBJECT_EVENT_PATTERN,
                        String.format(FORMAT_ID_TEXT, eventPatternId),
                        OPERATION_UNLINK,
                        OBJECT_EVENT_TYPE,
                        String.format(FORMAT_ID_TEXT, eventTypeId),
                        OPERATION_WORD_NOT
                );
                throw new LinkException(exceptionMessage);
            }
        }
        // Update data in database
        eventTypeDao.save(retrievedEventType);
        eventPatternDao.save(retrievedEventPattern);
    }

    @Override
    public void delete(Integer id) {
        Optional<EventPattern> retrievedEventPattern = eventPatternDao.findById(id);
        if (retrievedEventPattern.isPresent())
            eventPatternDao.delete(retrievedEventPattern.get());
        else {
            String exceptionMessage = String.format(
                    MESSAGE_NOT_FOUND_EXCEPTION,
                    OBJECT_EVENT_PATTERN,
                    String.format(FORMAT_ID_TEXT, id)
            );
            throw new NotFoundException(exceptionMessage);
        }
    }

}
