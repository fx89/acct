package com.desolatetimelines.acct.workspace.config;


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
@EnableJpaRepositories(value = "com.desolatetimelines.acct.workspace.springrepository")
@EntityScan(value = "com.desolatetimelines.acct.workspace.model")
public class AcctWorkspaceRepoSpringDataAdditionalConfig {

    @Bean
    public DataSource getDataSource(
        @Value("${WORKSPACE_DB_HOST}") String dbHost,
        @Value("${WORKSPACE_DB_PORT}") String dbPort,
        @Value("${WORKSPACE_DB_NAME}") String dbName,
        @Value("${WORKSPACE_DB_USERNAME}") String dbUsername,
        @Value("${WORKSPACE_DB_PASSWORD}") String dbPassword
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
