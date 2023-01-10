package org.micro.orderservice.external.requests;

import lombok.*;
import org.micro.orderservice.model.PaymentMode;
import java.io.Serializable;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class PaymentRequest implements Serializable {

    private long orderId;
    private double paymentTotal;
    private String paymentTransactionNumber;
    private PaymentMode paymentMode;
}