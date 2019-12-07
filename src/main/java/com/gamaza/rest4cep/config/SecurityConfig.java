package com.gamaza.rest4cep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Security Configuration Class
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* Private variables for injection */
    private final UserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Constructor injection
     * @param userDetailsService **userDetailsService**
     */
    public SecurityConfig(final UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    /**
     * User authentication check
     * @param auth **auth**
     * @throws Exception **Exception**
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Security configuration
     * @param http **http**
     * @throws Exception **Exception**
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
                .and()
            .authorizeRequests().antMatchers("/design/**").hasAnyRole("DESIGN")
                .and()
            .authorizeRequests().antMatchers("/runtime/**").hasAnyRole("RUNTIME")
                .and()
            .httpBasic()
                .and()
            .csrf().disable();
    }

}
