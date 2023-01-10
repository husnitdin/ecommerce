package org.micro.orderservice.service;

import lombok.extern.log4j.Log4j2;
import org.micro.orderservice.entity.Order;
import org.micro.orderservice.exception.CustomException;
import org.micro.orderservice.external.client.PaymentService;
import org.micro.orderservice.external.client.ProductService;
import org.micro.orderservice.external.requests.PaymentRequest;
import org.micro.orderservice.external.response.PaymentResponse;
import org.micro.orderservice.external.response.ProductResponse;
import org.micro.orderservice.model.OrderRequest;
import org.micro.orderservice.model.OrderResponse;
import org.micro.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public long placeAnOrder(OrderRequest orderRequest) {

        // Order Entity - save the data with status Created
        // Product service - check if the product available in stock and if yes reduce the quantity
        // Payment Service - pay if success => PLACED else FAILED

        log.info("placing order reqest: {}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating order with status Created");

        Order order = Order.builder()
                .orderStatus("Created")
                .orderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .orderProductQuantity(orderRequest.getQuantity())
                .orderTotalPrice(orderRequest.getTotalPrice())
                .build();
        order = orderRepository.save(order);

        log.info("calling payment service");

        ProductResponse productResponse
                = productService.fetchProductById(order.getProductId()).getBody();

        if (productResponse.getProductPrice() == 0 || productResponse.getProductPrice() < 0) {
            throw new CustomException("product not for sale", "PRODUCT_WITH_$0", 404);
        }

        if (orderRequest.getTotalPrice() < productResponse.getProductPrice()) {
            throw new CustomException("Not enough fund to pay", "NOT_ENOUGH_FOUND", 400);
        }

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .paymentTotal(orderRequest.getTotalPrice())
                .build();

        String orderStatus = null;

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred while payment is being processed");
            orderStatus = "FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("order placed successfully with order id: {}", order.getId());

        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("getting order details with order id: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found", "NOT_FOUND", 404));

        PaymentResponse transactionDetails
                = paymentService.getTransactionDetails(orderId).getBody();

        ProductResponse productResponse
                = productService.fetchProductById(order.getProductId()).getBody();

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getOrderTotalPrice())
                .orderDate(order.getOrderDate())
                .productResponse(productResponse)
                .paymentResponse(transactionDetails)
                .build();
    }
}