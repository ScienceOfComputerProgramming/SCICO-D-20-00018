package com.gamaza.rest4cep.runtime.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.runtime.dao.IncorrectEventTypeDao;
import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypeDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypePostDto;
import com.gamaza.rest4cep.runtime.mapper.IncorrectEventTypeMapper;
import com.gamaza.rest4cep.runtime.model.IncorrectEventType;
import com.gamaza.rest4cep.runtime.service.IncorrectEventTypeService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Incorrect Event Type Service Implementation
 */
@Service
public class IncorrectEventTypeServiceImpl implements IncorrectEventTypeService {

    // Private variables for injection
    private final IncorrectEventTypeDao incorrectEventTypeDao;
    private final IncorrectEventTypeMapper incorrectEventTypeMapper;

    /**
     * Constructor Injection
     */
    public IncorrectEventTypeServiceImpl(IncorrectEventTypeDao incorrectEventTypeDao, IncorrectEventTypeMapper incorrectEventTypeMapper) {
        this.incorrectEventTypeDao = incorrectEventTypeDao;
        this.incorrectEventTypeMapper = incorrectEventTypeMapper;
    }

    @Override
    public IncorrectEventTypeDto create(IncorrectEventTypePostDto incorrectEventTypePostDto) {
        IncorrectEventType createdIncorrectEventType = incorrectEventTypeDao.save(
                incorrectEventTypeMapper.mapToEntity(incorrectEventTypePostDto)
        );
        return incorrectEventTypeMapper.mapToDto(createdIncorrectEventType);
    }

    @Override
    public List<IncorrectEventTypeDto> readAll() {
        return Lists.newArrayList(incorrectEventTypeDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(incorrectEventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncorrectEventTypeDto readOneById(String id) {
        IncorrectEventType retrievedIncorrectEventType = incorrectEventTypeDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_INCORRECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return incorrectEventTypeMapper.mapToDto(retrievedIncorrectEventType);
    }

    @Override
    public List<IncorrectEventTypeDto> readLast5() {
        return Lists.newArrayList(incorrectEventTypeDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(incorrectEventTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        incorrectEventTypeDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        IncorrectEventType retrievedIncorrectEventType = incorrectEventTypeDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_INCORRECT_EVENT_TYPE,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        incorrectEventTypeDao.delete(retrievedIncorrectEventType);
    }

}
