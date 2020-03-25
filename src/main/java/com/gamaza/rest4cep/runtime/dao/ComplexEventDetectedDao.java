package com.gamaza.rest4cep.runtime.dao;

import com.gamaza.rest4cep.runtime.model.ComplexEventDetected;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Complex Events Detected Data Access Object
 */
@Repository
public interface ComplexEventDetectedDao extends MongoRepository<ComplexEventDetected, String> {

    /**
     * Get all Complex Events Detected in database ordered by date
     */
    List<ComplexEventDetected> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Complex Events Detected in database
     */
    List<ComplexEventDetected> findTop5ByOrderByCreatedDateDesc();

}
