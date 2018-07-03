package com.rafaniander.casadocodigo.greendogdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.rafaniander")
public class GreendogdeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreendogdeliveryApplication.class, args);
    }
}
