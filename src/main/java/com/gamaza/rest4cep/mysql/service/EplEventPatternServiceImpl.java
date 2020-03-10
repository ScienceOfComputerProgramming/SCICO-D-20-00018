package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dao.EplEventPatternDao;
import com.gamaza.rest4cep.mysql.dao.EventTypeDao;
import com.gamaza.rest4cep.mysql.dto.EplEventPatternWithListDto;
import com.gamaza.rest4cep.mysql.exception.EplEventPatternDeployedException;
import com.gamaza.rest4cep.mysql.exception.EventTypeException;
import com.gamaza.rest4cep.mysql.model.EplEventPattern;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EPL Event Patterns Service Implementation
 */
@Service
public class EplEventPatternServiceImpl implements EplEventPatternService {

    /* Private variables for injection */
    private final EplEventPatternDao eplEventPatternDao;
    private final EventTypeDao eventTypeDao;
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Constructor injection
     *
     * @param eplEventPatternDao **eplEventPatternDao**
     * @param eventTypeDao       **eventTypeDao**
     * @param dozerBeanMapper    **dozerBeanMapper**
     */
    public EplEventPatternServiceImpl(final EplEventPatternDao eplEventPatternDao, final EventTypeDao eventTypeDao, final DozerBeanMapper dozerBeanMapper) {
        this.eplEventPatternDao = eplEventPatternDao;
        this.eventTypeDao = eventTypeDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public EplEventPatternWithListDto create(EplEventPatternWithListDto eplEventPatternWithListDto) {
        return dozerBeanMapper.map(eplEventPatternDao.save(dozerBeanMapper.map(eplEventPatternWithListDto, EplEventPattern.class)), EplEventPatternWithListDto.class);
    }

    @Override
    public List<EplEventPatternWithListDto> readAll() {
        return Lists.newArrayList(eplEventPatternDao.findAll()).stream().map(eplPattern -> dozerBeanMapper.map(eplPattern, EplEventPatternWithListDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<EplEventPatternWithListDto> readAllByIsDeployed(boolean status) {
        return Lists.newArrayList(eplEventPatternDao.findAllByIsDeployed(status).stream().map(eplPattern -> dozerBeanMapper.map(eplPattern, EplEventPatternWithListDto.class)).collect(Collectors.toList()));
    }

    @Override
    public List<EplEventPatternWithListDto> readAllByIsInEsper(boolean status) {
        return Lists.newArrayList(eplEventPatternDao.findAllByIsInEsper(status).stream().map(eplPattern -> dozerBeanMapper.map(eplPattern, EplEventPatternWithListDto.class)).collect(Collectors.toList()));
    }

    @Override
    public EplEventPatternWithListDto readOneById(Integer id) {
        return dozerBeanMapper.map(eplEventPatternDao.findById(id).orElseGet(EplEventPattern::new), EplEventPatternWithListDto.class);
    }

    @Override
    public EplEventPatternWithListDto readOneByName(String name) {
        return dozerBeanMapper.map(eplEventPatternDao.findByName(name).orElseGet(EplEventPattern::new), EplEventPatternWithListDto.class);
    }

    @Override
    public void update(Integer id, EplEventPatternWithListDto eplEventPatternWithListDto) {
        eplEventPatternDao.findById(id).ifPresent(eplEventPattern -> {
            if (!eplEventPattern.isDeployed()) {
                eplEventPatternWithListDto.setId(id);
                eplEventPatternDao.save(dozerBeanMapper.map(eplEventPatternWithListDto, EplEventPattern.class));
            } else
                throw new EplEventPatternDeployedException("Can not update a deployed EPL Event Pattern");
        });
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        eplEventPatternDao.findById(id).ifPresent(eplEventPattern -> {
            if (status) {
                //if the EPL Event Pattern has not linked Event Types, it cannot be deployed
                if (eplEventPattern.getEventTypes().isEmpty())
                    throw new EventTypeException("EPL Event Pattern has not Event Types linked");
                    //If the EPL Event Pattern has disabled Event Types, it cannot be deployed
                else eplEventPattern.getEventTypes().forEach(eventType -> {
                    if (!eventType.isEnabled()) throw new EventTypeException("Some Event Type linked at EPL Event Pattern is disabled");
                });
            }
            eplEventPatternDao.updateStatus(id, status);
        });
    }

    @Override
    public void setPatternLink(Integer eplEventPatternId, Integer eventTypeId, boolean linkStatus) {
        eplEventPatternDao.findById(eplEventPatternId).ifPresent(eplEventPattern -> eventTypeDao.findById(eventTypeId).ifPresent(eventType -> {
            if (linkStatus) {
                if (!eplEventPattern.getEventTypes().contains(eventType)) {
                    eplEventPattern.getEventTypes().add(eventType);
                    eventType.getEplEventPatterns().add(eplEventPattern);
                }
            } else {
                if (eplEventPattern.getEventTypes().contains(eventType)) {
                    eplEventPattern.getEventTypes().remove(eventType);
                    eventType.getEplEventPatterns().remove(eplEventPattern);
                }
            }
            eventTypeDao.save(eventType);
            eplEventPatternDao.save(eplEventPattern);
        }));
    }

    @Override
    public void delete(Integer id) {
        eplEventPatternDao.deleteById(id);
    }

}
