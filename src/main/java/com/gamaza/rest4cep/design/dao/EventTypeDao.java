package com.gamaza.rest4cep.design.dao;

import com.gamaza.rest4cep.design.model.EventType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.gamaza.rest4cep.config.constant.EntityConstants.RELATION_FIELD_EVENT_PATTERNS;

/**
 * Event Type Data Access Object
 */
@Repository
public interface EventTypeDao extends CrudRepository<EventType, Integer> {

    /**
     * Search all readyToDeploy/notReadyToDeploy Event Types in database
     */
    List<EventType> findAllByReadyToDeploy(boolean status);

    /**
     * Search all deployed/undeployed Event Types in database
     */
    List<EventType> findAllByDeployed(boolean status);

    /**
     * Search Event Types in database by id (Override)
     */
    @Override
    @EntityGraph(attributePaths = RELATION_FIELD_EVENT_PATTERNS)
    Optional<EventType> findById(Integer id);

    /**
     * Search Event Types in database by name
     */
    @EntityGraph(attributePaths = RELATION_FIELD_EVENT_PATTERNS)
    Optional<EventType> findByName(String name);

}
