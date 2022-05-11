package com.micropos.order.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Order;
import com.micropos.carts.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService, Serializable {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Override
    public void supply(Order order) {
        System.out.println("order supplied");
        streamBridge.send("receive", order);
    }

    @Override
    public Order checkout() {
        Order order = getOrder();
        supply(order);
        return order;
    }

    public Order getOrder() {
        ResponseEntity<Cart> orderResponseEntity = restTemplate.
                getForEntity("http://cart-service/api/carts", Cart.class);
        Order order = new Order(orderResponseEntity.getBody());
        return order;
    }
}
