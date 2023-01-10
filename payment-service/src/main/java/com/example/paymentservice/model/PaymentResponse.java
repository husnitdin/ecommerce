package com.example.paymentservice.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class PaymentResponse implements Serializable {

    private long transactionId;
    private String paymentStatus;
    private PaymentMode paymentMode;
    private Instant paymentDate;
    private double paymentTotal;
    private long orderId;
}