package com.desolatetimelines.acct.reporting.dataprovider.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class AcctWorkspaceDataSourceProvider {

    private final String dbHost;
    private final String dbPort;
    private final String dbName;
    private final String dbUsername;
    private final String dbPassword;

    public AcctWorkspaceDataSourceProvider(
        @Value("${WORKSPACE_DB_HOST}") String dbHost,
        @Value("${WORKSPACE_DB_PORT}") String dbPort,
        @Value("${WORKSPACE_DB_NAME}") String dbName,
        @Value("${WORKSPACE_DB_USERNAME}") String dbUsername,
        @Value("${WORKSPACE_DB_PASSWORD}") String dbPassword
    ) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public DataSource createNewDataSource() {
        // Create the data source
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Configure the data source
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        // Return a reference to the data source
        return dataSource;
    }

}
