package com.desolatetimelines.acct.usermanagement.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan("com.desolatetimelines.acct")
@EnableFeignClients(basePackages = "com.desolatetimelines.acct")
public class AcctUserManagementWsApplication {

    public static void main(String... args) {
        SpringApplication.run(AcctUserManagementWsApplication.class);
    }

}
