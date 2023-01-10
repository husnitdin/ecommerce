package com.example.paymentservice.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class PaymentRequest implements Serializable {

    private long orderId;
    private double paymentTotal;
    private String paymentTransactionNumber;
    private PaymentMode paymentMode;
}