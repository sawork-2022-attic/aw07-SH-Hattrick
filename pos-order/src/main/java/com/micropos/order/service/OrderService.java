package com.micropos.order.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Order;
import com.micropos.carts.model.Product;

public interface OrderService {

    public void supply(Order order);

    public Order checkout();
}
