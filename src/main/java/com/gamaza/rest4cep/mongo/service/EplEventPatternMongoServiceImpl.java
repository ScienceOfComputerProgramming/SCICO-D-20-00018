package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dao.EplEventPatternMongoDao;
import com.gamaza.rest4cep.mongo.dto.EplEventPatternMongoDto;
import com.gamaza.rest4cep.mongo.model.EplEventPatternMongo;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EPL Event Patterns (Mongo version) Service Implementation
 */
@Service
public class EplEventPatternMongoServiceImpl implements EplEventPatternMongoService {

    /* Private variables for injection */
    private final EplEventPatternMongoDao eplEventPatternMongoDao;
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Constructor Injection
     * @param eplEventPatternMongoDao **eplEventPatternMongoDao**
     * @param dozerBeanMapper **dozerBeanMapper**
     */
    public EplEventPatternMongoServiceImpl(final EplEventPatternMongoDao eplEventPatternMongoDao, final DozerBeanMapper dozerBeanMapper){
        this.eplEventPatternMongoDao = eplEventPatternMongoDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public EplEventPatternMongoDto create(EplEventPatternMongoDto eplEventPatternMongoDto) {
        return dozerBeanMapper.map(eplEventPatternMongoDao.save(dozerBeanMapper.map(eplEventPatternMongoDto, EplEventPatternMongo.class)), EplEventPatternMongoDto.class);
    }

    @Override
    public List<EplEventPatternMongoDto> readAll() {
        return Lists.newArrayList(eplEventPatternMongoDao.findAllByOrderByInsertionDateDesc()).stream().map(eplEventPatternMongo -> dozerBeanMapper.map(eplEventPatternMongo, EplEventPatternMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public EplEventPatternMongoDto readOneById(String id) {
        return dozerBeanMapper.map(eplEventPatternMongoDao.findById(id).orElseGet(EplEventPatternMongo::new), EplEventPatternMongoDto.class);
    }

    @Override
    public List<EplEventPatternMongoDto> readLast5() {
        return Lists.newArrayList(eplEventPatternMongoDao.findTop5ByOrderByInsertionDateDesc()).stream().map(eplEventPatternMongo -> dozerBeanMapper.map(eplEventPatternMongo, EplEventPatternMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        eplEventPatternMongoDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        eplEventPatternMongoDao.deleteById(id);
    }

}
