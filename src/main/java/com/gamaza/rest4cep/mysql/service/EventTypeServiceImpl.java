package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dao.EplEventPatternDao;
import com.gamaza.rest4cep.mysql.dao.EventTypeDao;
import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;
import com.gamaza.rest4cep.mysql.exception.EventTypeException;
import com.gamaza.rest4cep.mysql.model.EventType;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Event Types Service Implementation
 */
@Service
public class EventTypeServiceImpl implements EventTypeService {

    /* Private variables for injection */
    private final EventTypeDao eventTypeDao;
    private final EplEventPatternDao eplEventPatternDao;
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Constructor injection
     * @param eventTypeDao **eventTypeDao**
     * @param eplEventPatternDao **eplEventPatternDao**
     * @param dozerBeanMapper **dozerBeanMapper**
     */
    public EventTypeServiceImpl(final EventTypeDao eventTypeDao, final EplEventPatternDao eplEventPatternDao, final DozerBeanMapper dozerBeanMapper){
        this.eventTypeDao = eventTypeDao;
        this.eplEventPatternDao = eplEventPatternDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public EventTypeWithListDto create(EventTypeWithListDto eventTypeWithListDto) {
        return dozerBeanMapper.map(eventTypeDao.save(dozerBeanMapper.map(eventTypeWithListDto, EventType.class)), EventTypeWithListDto.class);
    }

    @Override
    public List<EventTypeWithListDto> readAll() {
        return Lists.newArrayList(eventTypeDao.findAll()).stream().map(eventType -> dozerBeanMapper.map(eventType, EventTypeWithListDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventTypeWithListDto> readAllByIsEnabled(boolean status) {
        return Lists.newArrayList(eventTypeDao.findAllByIsEnabled(status)).stream().map(eventType -> dozerBeanMapper.map(eventType, EventTypeWithListDto.class)).collect(Collectors.toList());
    }

    @Override
    public EventTypeWithListDto readOneById(Integer id) {
        return dozerBeanMapper.map(eventTypeDao.findById(id).orElseGet(EventType::new), EventTypeWithListDto.class);
    }

    @Override
    public EventTypeWithListDto readOneByName(String name) {
        return dozerBeanMapper.map(eventTypeDao.findByName(name).orElseGet(EventType::new), EventTypeWithListDto.class);
    }

    @Override
    public EventTypeWithListDto readOneByChannelId(Integer channelId) {
        return dozerBeanMapper.map(eventTypeDao.findByChannel(channelId).orElseGet(EventType::new), EventTypeWithListDto.class);
    }

    @Override
    public void update(Integer id, EventTypeWithListDto eventTypeWithListDto) {
        //Search the eventType in database for update only if exists
        eventTypeDao.findById(id).ifPresent(eventType -> {
            if(!eventType.isEnabled()) {
                eventTypeWithListDto.setId(id);
                eventTypeDao.save(dozerBeanMapper.map(eventTypeWithListDto, EventType.class));
            }
            else
                throw new EventTypeException("The Event Type has to be disabled for update");
        });
    }

    @Override
    public void updateStatus(Integer id, boolean status) {
        //Update status only if the Event Type exists
        eventTypeDao.findById(id).ifPresent(eventType -> {
            //Undeploy all linked EPL Event Patterns if status = false
            if(!status)
                eventType.getEplEventPatterns().forEach(eplEventPattern -> eplEventPatternDao.updateStatus(eplEventPattern.getId(), false));
            //Update Event Type status
            eventType.setEnabled(status);
            eventTypeDao.save(eventType);
        });
    }

    @Override
    public void delete(Integer id) {
        //Delete the Event Type only if exists
        eventTypeDao.findById(id).ifPresent(eventType -> {
            //Undeploy all linked EPL Event Patterns
            eventType.getEplEventPatterns().forEach(eplEventPattern -> eplEventPatternDao.updateStatus(eplEventPattern.getId(), false));
            //Delete the Event Type
            eventTypeDao.deleteById(id);
        });
    }

}
