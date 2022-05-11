package com.micropos.order;

import com.micropos.carts.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
public class OrderSourceApplication {
    private static final Logger log = LoggerFactory.getLogger(OrderSourceApplication.class);


    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();

    }

    public static void main(String[] args) {
        SpringApplication.run(OrderSourceApplication.class, args);
        log.info("The OrderService Application has started...");
    }
}
