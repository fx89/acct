package com.desolatetimelines.acct.security.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = "com.desolatetimelines.acct.security.springrepository")
@EntityScan(value = "com.desolatetimelines.acct.security.model")
public class AcctSecurityRepoSpringDataAdditionalConfig {

    @Bean
    public DataSource getDataSource(
        @Value("${SECURITY_DB_HOST}") String dbHost,
        @Value("${SECURITY_DB_PORT}") String dbPort,
        @Value("${SECURITY_DB_NAME}") String dbName,
        @Value("${SECURITY_DB_USERNAME}") String dbUsername,
        @Value("${SECURITY_DB_PASSWORD}") String dbPassword
    ) {
        // Create the Hikari config
        final HikariConfig hikariConfig = new HikariConfig();

        // Create a new data source and add it to the Hikari config
        hikariConfig.setDataSource(
            DataSourceBuilder.create()
                .url("jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName)
                .username(dbUsername)
                .password(dbPassword)
                .build()
        );

        // Set additional properties
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTimeout(20000);

        // Create a new Hikari data source using the aforementioned Hikari config
        return new HikariDataSource(hikariConfig);
    }

}
