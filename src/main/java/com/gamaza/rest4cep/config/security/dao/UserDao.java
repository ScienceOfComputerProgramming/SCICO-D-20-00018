package com.gamaza.rest4cep.config.security.dao;

import com.gamaza.rest4cep.config.security.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.gamaza.rest4cep.config.constant.EntityConstants.RELATION_FIELD_ROLES;

/**
 * User Data Access Object
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    /**
     * Search and return one User by the username given and being this enabled
     */
    @EntityGraph(attributePaths = RELATION_FIELD_ROLES)
    Optional<User> findByUsernameAndEnabledIsTrue(String username);

}
