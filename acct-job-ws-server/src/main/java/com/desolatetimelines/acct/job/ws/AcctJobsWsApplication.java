package com.desolatetimelines.acct.job.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan("com.desolatetimelines.acct")
public class AcctJobsWsApplication {

    public static void main(String... args) {
        SpringApplication.run(AcctJobsWsApplication.class);
    }

}
