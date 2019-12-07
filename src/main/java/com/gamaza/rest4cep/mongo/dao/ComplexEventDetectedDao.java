package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.ComplexEventDetectedMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Complex Events Detected (Mongo version) Dao
 */
@Repository
public interface ComplexEventDetectedDao extends MongoRepository<ComplexEventDetectedMongo, String> {

    /**
     * Get all Complex Events Detected in database ordered by date
     * @return complexEventsDetected list
     */
    List<ComplexEventDetectedMongo> findAllByOrderByInsertionDateDesc();

    /**
     * Get Last 5 EPL Event Patterns in database
     * @return Last 5 eplEventPatterns list
     */
    List<ComplexEventDetectedMongo> findTop5ByOrderByInsertionDateDesc();

}
