package com.example.products.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductResponse {

    private long productId;
    private String productCode;
    private String productName;
    private double productPrice;
    private long productQuantity;
}
