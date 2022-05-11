package com.micropos.carts.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<Item> items = new ArrayList<>();
    private Status status;
    public Order(Cart cart) {
        this.items = cart.getItems();
        status = Status.PENDING;
    }
    public Order(){}

    public List<Item> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

