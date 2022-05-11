package com.micropos.delivery;

import com.micropos.carts.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


import java.util.function.Consumer;

@EnableEurekaClient
@SpringBootApplication
public class OrderCheckApplication {

    private static final Logger log = LoggerFactory.getLogger(OrderCheckApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderCheckApplication.class, args);
        log.info("The Ordercheck Application has started...");
    }

    @Bean
    public Consumer<Order> checkOrder() {
        return new OrderChecker();
    }
}
