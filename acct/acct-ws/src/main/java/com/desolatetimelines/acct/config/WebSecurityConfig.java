package com.desolatetimelines.acct.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passEnc = new NonEncodingPasswordEncoder();

    private final AppUserProperties appuserProperties;

    public WebSecurityConfig(@Autowired AppUserProperties appuserProperties) {
        this.appuserProperties = appuserProperties;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .passwordEncoder(passEnc)
            .withUser(appuserProperties.getUsername()).password(appuserProperties.getPassword()).roles("ADMIN");
    }

    private static class NonEncodingPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence charSequence) {
            if (charSequence == null) {
                return "";
            }

            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            if (charSequence == null || s == null) {
                return false;
            }

            return charSequence.toString().equals(s);
        }
    }
}
