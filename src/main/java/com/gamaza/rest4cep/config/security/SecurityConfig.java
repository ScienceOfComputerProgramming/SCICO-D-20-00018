package com.gamaza.rest4cep.config.security;

import com.gamaza.rest4cep.config.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.gamaza.rest4cep.config.constant.ConfigConstants.ROLE_DESIGN;
import static com.gamaza.rest4cep.config.constant.ConfigConstants.ROLE_RUNTIME;

/**
 * Security Configuration Class
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Private variables for injection
    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor injection
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .authorizeRequests().antMatchers("/design/**").hasAnyRole(ROLE_DESIGN)
                    .and()
                .authorizeRequests().antMatchers("/runtime/**").hasAnyRole(ROLE_RUNTIME)
                    .and()
                .httpBasic()
                    .and()
                .csrf().disable();
    }

}
