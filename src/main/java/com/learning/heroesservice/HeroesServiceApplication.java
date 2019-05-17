package com.learning.heroesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan("com.learning.heroesservice")
public class HeroesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesServiceApplication.class, args);
    }
}
