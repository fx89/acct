package com.desolatetimelines.acct.usage.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan("com.desolatetimelines.acct")
public class AcctUsageWsApplication {

    public static void main(String... args) {
        SpringApplication.run(AcctUsageWsApplication.class);
    }

}
