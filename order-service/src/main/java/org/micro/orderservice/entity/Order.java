package org.micro.orderservice.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "order_detail")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String orderStatus;

    @NotNull
    private Instant orderDate;

    private long productId;

    @NotNull
    private long orderProductQuantity;

    @NotNull
    private double orderTotalPrice;

}