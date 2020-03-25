package com.gamaza.rest4cep.design.dao;

import com.gamaza.rest4cep.design.model.EventPattern;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.gamaza.rest4cep.config.constant.EntityConstants.RELATION_FIELD_EVENT_TYPES;

/**
 * Event Pattern Data Access Object
 */
@Repository
public interface EventPatternDao extends CrudRepository<EventPattern, Integer> {

    /**
     * Search Event Patterns in database by id (Override)
     */
    @Override
    @EntityGraph(attributePaths = RELATION_FIELD_EVENT_TYPES)
    Optional<EventPattern> findById(Integer id);

    /**
     * Search Event Patterns in database by name
     */
    @EntityGraph(attributePaths = RELATION_FIELD_EVENT_TYPES)
    Optional<EventPattern> findByName(String name);

    /**
     * Search all readyToDeploy/notReadyToDeploy Event Patterns in database
     */
    List<EventPattern> findAllByReadyToDeploy(boolean status);

    /**
     * Search all deployed/undeployed Event Patterns in database
     */
    List<EventPattern> findAllByDeployed(boolean status);

    /**
     * Update the Event Pattern status by id given
     */
    @Modifying
    @Transactional
    @Query("UPDATE EventPattern SET readyToDeploy = :status, deployed = 0 WHERE id = :id")
    void updateStatus(@Param(value = "id") Integer id, @Param(value = "status") boolean status);

}
