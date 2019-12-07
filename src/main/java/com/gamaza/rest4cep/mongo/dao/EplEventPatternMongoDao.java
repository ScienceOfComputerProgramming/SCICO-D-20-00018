package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.EplEventPatternMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EPL Event Patterns (Mongo version) Dao
 */
@Repository
public interface EplEventPatternMongoDao extends MongoRepository<EplEventPatternMongo, String> {

    /**
     * Get all EPL Event Patterns in database ordered by date
     * @return eplEventPatterns list
     */
    List<EplEventPatternMongo> findAllByOrderByInsertionDateDesc();

    /**
     * Get Last 5 EPL Event Patterns in database
     * @return Last 5 eplEventPatterns list
     */
    List<EplEventPatternMongo> findTop5ByOrderByInsertionDateDesc();

}
