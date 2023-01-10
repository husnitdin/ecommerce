package org.micro.orderservice.controller;

import lombok.extern.log4j.Log4j2;
import org.micro.orderservice.model.OrderRequest;
import org.micro.orderservice.model.OrderResponse;
import org.micro.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
@Log4j2
public class OrderV1Controller {

    @Autowired
    private OrderService orderService;

//    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/place-order")
    public ResponseEntity<Long> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeAnOrder(orderRequest);
        log.info("place order with order id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") long orderId) {

        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

}