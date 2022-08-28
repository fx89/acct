package com.desolatetimelines.acct.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUserProperties {

    private final String username;

    private final String password;

    public AppUserProperties(
        @Value("${acct.app.user.name}") String username,
        @Value("${acct.app.user.password}") String password
    ) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
