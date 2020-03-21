package com.gamaza.rest4cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "com.gamaza.rest4cep.mysql.dao",
        "com.gamaza.rest4cep.config.security.dao"
})
@EnableMongoRepositories(basePackages = "com.gamaza.rest4cep.mongo.dao")
@EnableJpaAuditing
@EnableMongoAuditing
public class Rest4CepApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rest4CepApplication.class, args);
    }

}
