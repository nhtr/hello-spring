package com.nhtr.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.nhtr.accountservice.entity")
@ComponentScan({
        "com.nhtr.accountservice.config",
        "com.nhtr.accountservice.service",
        "com.nhtr.accountservice.controller",
        "com.nhtr.accountservice.mapper"
})
@ConfigurationPropertiesScan({"com.nhtr.accountservice.properties"})
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
