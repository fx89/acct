package com.desolatetimelines.acct.batch.currencycollector;

import ch.qos.logback.classic.util.ContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry point of the currency batch collector. The purpose of the currency
 * batch collector is to download currency records on a daily basis and write
 * the value into files which can then be read by other processes.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.desolatetimelines.acct"})
public class CurrencyBatchCollectApplication {

    public static void main(String[] args) {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "logback.xml");
        ApplicationContext appContext = SpringApplication.run(CurrencyBatchCollectApplication.class, args);

        CurrencyBatchCollectMain main = appContext.getBean(CurrencyBatchCollectMain.class);
        main.run();
    }

}
