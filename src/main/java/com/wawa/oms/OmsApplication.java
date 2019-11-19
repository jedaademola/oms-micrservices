package com.wawa.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
    }

    //LOG.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);
    //LOG.error("Failed to process {}", someUsefulContext, exception);
}
