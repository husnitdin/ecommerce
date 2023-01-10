package org.micro.orderservice.service;

import org.micro.orderservice.model.OrderRequest;
import org.micro.orderservice.model.OrderResponse;

public interface OrderService {

    long placeAnOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}