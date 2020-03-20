package com.gamaza.rest4cep.mysql.dao;

import com.gamaza.rest4cep.mysql.model.EventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Event Type Data Access Object
 */
@Repository
public interface EventTypeDao extends CrudRepository<EventType, Integer> {

    /**
     * Get all enabled/disabled Event Types in database
     */
    List<EventType> findAllByEnabled(boolean status);

    /**
     * Search Event Types in database by name
     */
    Optional<EventType> findByName(String name);

    /**
     * Search Event Types in database by channel id
     */
    Optional<EventType> findByChannel(Integer channelId);

}
