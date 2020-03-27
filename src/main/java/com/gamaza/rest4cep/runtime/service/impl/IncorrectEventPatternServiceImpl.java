package com.gamaza.rest4cep.runtime.service.impl;

import com.gamaza.rest4cep.config.exception.NotFoundException;
import com.gamaza.rest4cep.runtime.dao.IncorrectEventPatternDao;
import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternPostDto;
import com.gamaza.rest4cep.runtime.mapper.IncorrectEventPatternMapper;
import com.gamaza.rest4cep.runtime.model.IncorrectEventPattern;
import com.gamaza.rest4cep.runtime.service.IncorrectEventPatternService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gamaza.rest4cep.config.constant.ExceptionConstants.*;

/**
 * Incorrect Event Pattern Service Implementation
 */
@Service
public class IncorrectEventPatternServiceImpl implements IncorrectEventPatternService {

    // Private variables for injection
    private final IncorrectEventPatternDao incorrectEventPatternDao;
    private final IncorrectEventPatternMapper incorrectEventPatternMapper;

    /**
     * Constructor Injection
     */
    public IncorrectEventPatternServiceImpl(IncorrectEventPatternDao incorrectEventPatternDao, IncorrectEventPatternMapper incorrectEventPatternMapper) {
        this.incorrectEventPatternDao = incorrectEventPatternDao;
        this.incorrectEventPatternMapper = incorrectEventPatternMapper;
    }

    @Override
    public IncorrectEventPatternDto create(IncorrectEventPatternPostDto incorrectEventPatternPostDto) {
        IncorrectEventPattern createdIncorrectEventPattern = incorrectEventPatternDao.save(
                incorrectEventPatternMapper.mapToEntity(incorrectEventPatternPostDto)
        );
        return incorrectEventPatternMapper.mapToDto(createdIncorrectEventPattern);
    }

    @Override
    public List<IncorrectEventPatternDto> readAll() {
        return Lists.newArrayList(incorrectEventPatternDao.findAllByOrderByCreatedDateDesc())
                .stream()
                .map(incorrectEventPatternMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncorrectEventPatternDto readOneById(String id) {
        IncorrectEventPattern retrievedIncorrectEventPattern = incorrectEventPatternDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_INCORRECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        return incorrectEventPatternMapper.mapToDto(retrievedIncorrectEventPattern);
    }

    @Override
    public List<IncorrectEventPatternDto> readLast5() {
        return Lists.newArrayList(incorrectEventPatternDao.findTop5ByOrderByCreatedDateDesc())
                .stream()
                .map(incorrectEventPatternMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        incorrectEventPatternDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        IncorrectEventPattern retrievedIncorrectEventPattern = incorrectEventPatternDao.findById(id).orElseThrow(
                () -> {
                    String exceptionMessage = String.format(
                            MESSAGE_NOT_FOUND_EXCEPTION,
                            OBJECT_INCORRECT_EVENT_PATTERN,
                            String.format(FORMAT_ID_TEXT, id)
                    );
                    throw new NotFoundException(exceptionMessage);
                }
        );
        incorrectEventPatternDao.delete(retrievedIncorrectEventPattern);
    }

}
