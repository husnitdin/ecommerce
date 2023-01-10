package org.micro.orderservice.model;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class OrderRequest implements Serializable {

    private long productId;
    private long quantity;
    private double totalPrice;
    private PaymentMode paymentMode;
}
