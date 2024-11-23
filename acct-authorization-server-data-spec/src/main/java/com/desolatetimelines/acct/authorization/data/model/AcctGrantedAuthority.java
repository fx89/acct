package com.desolatetimelines.acct.authorization.data.model;

import org.springframework.security.core.GrantedAuthority;

import static java.util.Objects.requireNonNull;

public class AcctGrantedAuthority implements GrantedAuthority {
    private final String name;

    public AcctGrantedAuthority(String name) {
        requireNonNull(name, "No name provided for a granted authority");
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
