package com.desolatetimelines.acct.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.desolatetimelines.acct")
@EnableFeignClients(basePackages = "com.desolatetimelines.acct")
public class AcctAuthorizationServer {

    public static void main(String[] args) {
        SpringApplication.run(AcctAuthorizationServer.class);
    }

}
