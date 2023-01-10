package org.micro.orderservice.model;

import lombok.*;
import org.micro.orderservice.external.response.PaymentResponse;
import org.micro.orderservice.external.response.ProductResponse;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private double totalPrice;
    private ProductResponse productResponse;
    private PaymentResponse paymentResponse;
}
