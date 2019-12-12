package com.gamaza.rest4cep.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * Bean Configuration Class
 */
@Configuration
@PropertySource("classpath:application.yml")
public class BeanConfig {

    /* Private variables for injection */
    private final Environment environment;
    private final DataSource dataSource;

    /**
     * Constructor injection
     * @param environment **environment**
     * @param dataSource **datasource**
     */
    public BeanConfig(final Environment environment, final DataSource dataSource){
        this.environment = environment;
        this.dataSource = dataSource;
    }

    /**
     * Dozer Mapper Bean
     * @return Global mapper
     */
    @Bean(name = "dozerBeanMapper")
    public DozerBeanMapper dozerBeanMapper(){
        return new DozerBeanMapper();
    }

    /**
     * User details configuration Bean
     * @return User details authorization
     */
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource);
        jdbcImpl.setUsersByUsernameQuery(environment.getProperty("application.security.query.username"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getProperty("application.security.query.authorities"));
        return jdbcImpl;
    }

}
