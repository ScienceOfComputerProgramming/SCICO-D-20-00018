package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dao.EventTypeMongoDao;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.model.EventTypeMongo;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Event Types (Mongo version) Service Implementation
 */
@Service
public class EventTypeMongoServiceImpl implements EventTypeMongoService {

    /* Private variables for injection */
    private final EventTypeMongoDao eventTypeMongoDao;
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Constructor injection
     * @param eventTypeMongoDao **eventTypeMongoDao**
     * @param dozerBeanMapper **dozerBeanMapper**
     */
    public EventTypeMongoServiceImpl(final EventTypeMongoDao eventTypeMongoDao, final DozerBeanMapper dozerBeanMapper){
        this.eventTypeMongoDao = eventTypeMongoDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public EventTypeMongoDto create(EventTypeMongoDto eventTypeMongoDto) {
        return dozerBeanMapper.map(eventTypeMongoDao.save(dozerBeanMapper.map(eventTypeMongoDto, EventTypeMongo.class)), EventTypeMongoDto.class);
    }

    @Override
    public List<EventTypeMongoDto> readAll() {
        return Lists.newArrayList(eventTypeMongoDao.findAllByOrderByInsertionDateDesc()).stream().map(eventTypeMongo -> dozerBeanMapper.map(eventTypeMongo, EventTypeMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public EventTypeMongoDto readOneById(String id) {
        return dozerBeanMapper.map(eventTypeMongoDao.findById(id).orElseGet(EventTypeMongo::new), EventTypeMongoDto.class);
    }

    @Override
    public List<EventTypeMongoDto> readLast5() {
        return Lists.newArrayList(eventTypeMongoDao.findTop5ByOrderByInsertionDateDesc()).stream().map(eventTypeMongo -> dozerBeanMapper.map(eventTypeMongo, EventTypeMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        eventTypeMongoDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        eventTypeMongoDao.deleteById(id);
    }

}
