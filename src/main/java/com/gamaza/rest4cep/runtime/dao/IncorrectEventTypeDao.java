package com.gamaza.rest4cep.runtime.dao;

import com.gamaza.rest4cep.runtime.model.IncorrectEventType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Incorrect Event Type Data Access Object
 */
@Repository
public interface IncorrectEventTypeDao extends MongoRepository<IncorrectEventType, String> {

    /**
     * Get all Incorrect Event Types in database ordered by date
     */
    List<IncorrectEventType> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Incorrect Event Types in database
     */
    List<IncorrectEventType> findTop5ByOrderByCreatedDateDesc();

}
