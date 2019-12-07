package com.gamaza.rest4cep.mysql.dao;

import com.gamaza.rest4cep.mysql.model.EventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Event Types DAO
 */
@Repository
public interface EventTypeDao extends CrudRepository<EventType, Integer> {

    /**
     * Get all enabled/disabled Event Types in database
     * @param status **status**
     * @return eventType enabled/disabled list
     */
    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    List<EventType> findAllByIsEnabled(boolean status);

    /**
     * Search Event Types in database by name
     * @param name **name**
     * @return eventType found
     */
    Optional<EventType> findByName(String name);

    /**
     * Search Event Types in database by channel id
     * @param channelId **channelId**
     * @return eventType found
     */
    Optional<EventType> findByChannel(Integer channelId);

}
