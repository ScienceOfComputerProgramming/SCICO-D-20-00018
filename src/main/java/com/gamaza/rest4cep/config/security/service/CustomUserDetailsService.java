package com.gamaza.rest4cep.config.security.service;

import com.gamaza.rest4cep.config.security.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom User Details Service Implementation
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Private variables for injection
    private final UserDao userDao;

    /**
     * Constructor injection
     */
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDao.findByUsernameAndEnabledIsTrue(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
    }

}
