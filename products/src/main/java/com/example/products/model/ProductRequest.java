package com.example.products.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
//@JsonIgnoreProperties({"productId", "productCode"}) when you want to ignore multiple fields
public class ProductRequest implements Serializable {

    // @JsonIgnore  hide a field when object returned
    // @JsonIgnore
    private String productCode;
    private String productName;
    private double productPrice;
    private long productQuantity;
}
