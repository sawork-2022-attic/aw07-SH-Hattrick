package com.micropos.delivery;

import com.micropos.carts.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.function.Consumer;

public class OrderChecker implements Consumer<Order> {

    public static final Logger log = LoggerFactory.getLogger(OrderChecker.class);
    private static final Long MAX_ACCOUNT = 10000L;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void accept(Order order) {
        List<Item> items = order.getItems();
        int total_price = 0;
        for(Item i : items) {
            total_price+= (i.getQuantity()*i.getProduct().getPrice());
        }
        log.info("status : "+ order.getStatus() + " order accepted,total price is : "+ total_price);

        if (total_price > MAX_ACCOUNT) {
            order.setStatus(Status.DECLINED);
            streamBridge.send("order-declined", message(order));
        } else {
            order.setStatus(Status.APPROVED);
            streamBridge.send("order-approved", message(order));
        }

        log.info("status : "+ order.getStatus());

    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
    
}
