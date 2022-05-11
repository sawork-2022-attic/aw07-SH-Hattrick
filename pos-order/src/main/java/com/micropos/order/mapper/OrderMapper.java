package com.micropos.order.mapper;

import com.micropos.carts.dto.CartDto;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Order;
import com.micropos.order.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);

}