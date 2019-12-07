package com.gamaza.rest4cep.mysql.dao;

import com.gamaza.rest4cep.mysql.model.EplEventPattern;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * EPL Event Patterns DAO
 */
@Repository
@SuppressWarnings("SpringDataMethodInconsistencyInspection")
public interface EplEventPatternDao extends CrudRepository<EplEventPattern, Integer> {

    /**
     * Search EPL Event Patterns in database by name
     * @param name **name**
     * @return eplEventPattern found
     */
    Optional<EplEventPattern> findByName(String name);

    /**
     * Search all deployed/undeployed EPL Event Patterns in database
     * @param status **status**
     * @return eplEventPattern deployed/undeployed list
     */
    List<EplEventPattern> findAllByIsDeployed(boolean status);

    /**
     * Search all inEsper/notInEsper EPL Event Patterns in database
     * @param status **status**
     * @return eplEventPattern inEsper/notInEsper list
     */
    List<EplEventPattern> findAllByIsInEsper(boolean status);

    /**
     * Update the EPL Event Pattern status by id given
     * @param id **id**
     * @param status **status**
     */
    @Modifying @Transactional
    @Query("UPDATE EplEventPattern SET isDeployed = :status, isInEsper = 0 WHERE id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") boolean status);

}
