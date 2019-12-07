package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dao.ComplexEventDetectedDao;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.model.ComplexEventDetectedMongo;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Complex Events Detected Service Implementation
 */
@Service
public class ComplexEventDetectedMongoServiceImpl implements ComplexEventDetectedMongoService {

    /* Private variables for injection */
    private final ComplexEventDetectedDao complexEventDetectedDao;
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Constructor injection
     * @param complexEventDetectedDao **complexEventDetectedDao**
     * @param dozerBeanMapper **dozerBeanMapper**
     */
    public ComplexEventDetectedMongoServiceImpl(final ComplexEventDetectedDao complexEventDetectedDao, final DozerBeanMapper dozerBeanMapper) {
        this.complexEventDetectedDao = complexEventDetectedDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public ComplexEventDetectedMongoDto create(ComplexEventDetectedMongoDto complexEventDetectedMongoDto) {
        return dozerBeanMapper.map(complexEventDetectedDao.save(dozerBeanMapper.map(complexEventDetectedMongoDto, ComplexEventDetectedMongo.class)), ComplexEventDetectedMongoDto.class);
    }

    @Override
    public List<ComplexEventDetectedMongoDto> readAll() {
        return Lists.newArrayList(complexEventDetectedDao.findAllByOrderByInsertionDateDesc()).stream().map(complexEventDetectedMongo -> dozerBeanMapper.map(complexEventDetectedMongo, ComplexEventDetectedMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public ComplexEventDetectedMongoDto readOneById(String id) {
        return dozerBeanMapper.map(complexEventDetectedDao.findById(id).orElseGet(ComplexEventDetectedMongo::new), ComplexEventDetectedMongoDto.class);
    }

    @Override
    public List<ComplexEventDetectedMongoDto> readLast5() {
        return Lists.newArrayList(complexEventDetectedDao.findTop5ByOrderByInsertionDateDesc()).stream().map(complexEventDetectedMongo -> dozerBeanMapper.map(complexEventDetectedMongo, ComplexEventDetectedMongoDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        complexEventDetectedDao.deleteAll();
    }

    @Override
    public void deleteOne(String id) {
        complexEventDetectedDao.deleteById(id);
    }
}
