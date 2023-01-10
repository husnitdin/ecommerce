package com.example.paymentservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_detail")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long orderId;

    @NotNull
    private String paymentMode;

    @NotNull
    private String paymentTransactionNumber;

    @NotNull
    private Instant paymentDate;

    @NotNull
    private String paymentStatus;

    @NotNull
    private double paymentTotal;

}